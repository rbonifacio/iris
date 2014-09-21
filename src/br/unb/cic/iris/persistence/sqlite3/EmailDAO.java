/*
 * EmailDAO.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 18, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.persistence.sqlite3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.unb.cic.iris.core.EmailMessage;
import br.unb.cic.iris.core.db.IEmailDAO;

/**
 * An implementation of @see br.unb.cic.iris.core.db.EmailDAO 
 * using the SQLite databaese. 
 * 
 * @author rbonifacio
 *
 */
public class EmailDAO implements IEmailDAO {
	private static final String CONNECTION = "jdbc:sqlite:email.db";
	
	private static final String SQL_MESSAGE_TABLE_EXISTS = 
			"SELECT count(*) " +
			"FROM sqlite_master " +
		    "WHERE type = 'table' and name = 'TB_MESSAGE' ";
	
	private static final String CREATE_MESSAGE_TABLE = 
			"CREATE TABLE TB_MESSAGE(" +
			 "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
			 "ACCOUNT STRING, " +
			 "TOo STRING, " +
			 "CC STRING, " +
			 "BCC STRING, " +
			 "SUBJECT STRING, " +
			 "MESSAGE STRING)";

	private static final String CREATE_INCOME_MESSAGE_REQUEST_TABLE = 
			"CREATE TABLE TB_INCOME_MESSAGE_REQUEST(" +
			 "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
			 "DATE_TIME INTEGER, " +
			 "SEQ_NUMBER INTEGER)";
	
	private static final String SQL_INSERT_MESSAGE = 
			"INSERT INTO TB_MESSAGE" +
			 "(ACCOUNT, TOo, CC, BCC, SUBJECT, MESSAGE) " +
			 "VALUES(?, ?, ?, ?, ?, ?)";
	
	private static boolean persistenceMode = true;
	
	private static Logger logger = Logger.getLogger(EmailDAO.class.getName());
	
	static { 
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(CONNECTION);
			Statement statement = connection.createStatement();
	    	ResultSet rs = statement.executeQuery(SQL_MESSAGE_TABLE_EXISTS);
	    	
	    	//here we check weather the message table exists. 
	    	//if it does not exist, I've assumed that we must 
	    	//create all database tables. 
	    	if(! rs.next() || rs.getInt(1) == 0) {
	    		logger.info("creating the data base tables");
				createTables(connection);
			}
		}
		catch(Exception e) {
			System.out.println();
			logger.log(Level.SEVERE, "Error configuring the database");
			logger.log(Level.SEVERE, "Erro: " + e.getMessage());
			logger.log(Level.SEVERE, "Working on non persistence mode");
			System.out.println();
			persistenceMode = false;
		}
		finally{ 
			closeDatabaseConnection(connection);
		}
	}


	/*
	 * close the database connection
	 */
	private static void closeDatabaseConnection(Connection connection) {
		try {
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "could not close the database connection");
			throw new RuntimeException(e);
		}
	}
	
	
	/* the single instance of EmailDAO */
	private static EmailDAO instance;
	
	/* private constructor, according to the singleton pattern */
	private EmailDAO() { }

	/**
	 * Retrieves the singleton instance of EmailDAO. 
	 * @return the singleton instance of EmailDAO
	 */
	public static EmailDAO instance() {
		if(instance == null) {
			instance = new EmailDAO();
		}
		return instance;
	}
	

	/* (non-Javadoc)
	 * @see br.unb.cic.iris.core.db.EmailDAO#saveMessage(br.unb.cic.iris.core.EmailMessage)
	 */
	@Override
	public void saveMessage(EmailMessage message) {
		if(!persistenceMode) {
			logger.log(Level.INFO, "Note, for some reason, we could not save this operation into the database.");
			return;
		}
		Connection connection = null;
		try {
			logger.info("saving message into the database");
			
			connection = DriverManager.getConnection(CONNECTION);
			PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_MESSAGE);
			stmt.setString(1, message.getFrom());
			stmt.setString(2, message.getTo() == null ? "" : message.getTo());
			stmt.setString(3, message.getCc() == null ? "" : message.getCc());
			stmt.setString(4, message.getBcc() == null ? "" : message.getBcc());
			stmt.setString(5, message.getSubject() == null ? "" : message.getSubject());
			stmt.setString(6, message.getMessage() == null ? "" : message.getMessage());
			stmt.executeUpdate();
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		finally {
			closeDatabaseConnection(connection);
		}
	}

	/* (non-Javadoc)
	 * @see br.unb.cic.iris.core.db.EmailDAO#listSentMessages()
	 */
	@Override
	public List<EmailMessage> listSentMessages() {
		throw new RuntimeException("not implemented yet!");
	}

	/* (non-Javadoc)
	 * @see br.unb.cic.iris.core.db.EmailDAO#listIncomeMessages()
	 */
	@Override
	public List<EmailMessage> listIncomeMessages() {
		throw new RuntimeException("not implemented yet!");
	}
	

		
	/*
	 * create the database tables. 
	 */
	private static void createTables(Connection connection) throws Exception {
		Statement statement = connection.createStatement();
		statement.executeUpdate(CREATE_MESSAGE_TABLE);
		statement.executeUpdate(CREATE_INCOME_MESSAGE_REQUEST_TABLE);
	}


}
