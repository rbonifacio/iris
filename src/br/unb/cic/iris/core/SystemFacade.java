/*
 * SystemFacade.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 18, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.unb.cic.iris.core.db.IEmailDAO;
import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.persistence.sqlite3.EmailDAO;

/**
 * The facade class of the project. 
 * 
 * @author rbonifacio
 */
public class SystemFacade {

	private EmailClient client;

	public SystemFacade() {
		//client = new SimpleClient();
		client = new SilentEmailClient();
	}
	
	public void send(EmailMessage message) throws EmailException {
		try {
			Configuration c = Configuration.instance();
			
			if(!c.validConfiguraion()) {
				loadAllProperties(c);
			}
			
			client.send(message);
			
			IEmailDAO dao = EmailDAO.instance();
			
			dao.saveMessage(message);
		}
		catch(EmailException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "could not send the e-mail message");
			throw new RuntimeException(e);
		}
		catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/*
	 * Retrieve all account configurations from property files. 
	 */
	private void loadAllProperties(Configuration c) throws IOException, FileNotFoundException {
		c.fromProperties("config.properties");
		
		//set the account configurations
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File(c.accountPropertyFile())));
		
		//set the user and password properties
		c.setAccount(properties.getProperty(Configuration.MAIL_USER));
		c.setPassword(properties.getProperty(Configuration.MAIL_PASSWORD));
	}
}
