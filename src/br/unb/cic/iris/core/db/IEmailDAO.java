/*
 * EmailDAO.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 18, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core.db;

import java.util.List;

import br.unb.cic.iris.core.EmailMessage;

/**
 * A DAO for electronic e-mail messages. Note that the 
 * current version of the persistence concern is quite simple. 
 * Indeed, we have only one persistent class (an email message). 
 * 
 * @author rbonifacio
 */
public interface IEmailDAO {

	/**
	 * Saves an email message into the database. 
	 * 
	 * @param message
	 */
	public void saveMessage(EmailMessage message);

	/**
	 * Retrieves all sent messages from the 
	 * database.
	 *  
	 * @return the list of sent messages
	 */
	public List<EmailMessage> listSentMessages();
	
	/**
	 * Retrievas all income messages from the 
	 * database.
	 * 
	 * @return the list of income messages
	 */
	public List<EmailMessage> listIncomeMessages();
}
