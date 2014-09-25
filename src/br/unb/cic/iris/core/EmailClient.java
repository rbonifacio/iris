/*
 * EmailCliente
 * --------------------------------------
 *  version: 0.0.1
 *  date: September, 2014
 *  author: rbonifacio
 *  list of changes: (none)
 */
package br.unb.cic.iris.core;

import java.util.List;

import br.unb.cic.iris.core.exception.EmailException;

public interface EmailClient {

	/**
	 * Send an email message
	 * 
	 * @param message
	 * @throws EmailException
	 */
	public void send(EmailMessage message) throws EmailException;
	
	
	/**
	 * Retrieves all messages from a given seqnum
	 * 
	 * @param seqnum
	 * @return
	 * @throws EmailException
	 */
	public List<EmailMessage> getMessages(int seqnum) throws EmailException;
	
}
