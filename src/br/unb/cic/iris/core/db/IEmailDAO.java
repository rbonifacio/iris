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

import br.unb.cic.iris.core.Account;
import br.unb.cic.iris.core.EmailMessage;
import br.unb.cic.iris.core.Folder;

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
	 * A finder method that uses the folder description 
	 * as criteria. 
	 * @param description criteria used in the query
	 * @return A folder that satisfies the criteria
	 */
	public Folder findFolder(String description);
	
	/**
	 * A finder method that uses the account as 
	 * criteria
	 * @param account criteria used in the query
	 * @return An Account that satisfies the criteria
	 */
	public Account findAccount(String account);
	
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
