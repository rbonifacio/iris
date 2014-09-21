/*
 * EmailClientImpl.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 6, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.unb.cic.iris.core.exception.EmailException;

/**
 * @author rbonifacio
 */
public class SimpleClient implements EmailClient {


	/* (non-Javadoc)
	 * @see br.unb.cic.iris.core.EmailClient#send(br.unb.cic.iris.core.EmailMessage)
	 */
	@Override
	public void send(EmailMessage message) throws EmailException {
		try {
			Session session = createSession();
			send(message, session);
		}
		catch(Exception e) {
			throw new EmailException("error sending message to " + message.getFrom(), e);
		}
	}

	private void send(EmailMessage message, Session session) throws MessagingException, AddressException {
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(Configuration.instance().getAccount()));	
		mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(message.getTo()));
		
		if(message.getBcc() != null) {
			mimeMessage.addRecipient(RecipientType.BCC, new InternetAddress(message.getBcc()));
		}
		
		if(message.getCc() != null) {
			mimeMessage.addRecipient(RecipientType.CC, new InternetAddress(message.getCc()));
		}
		mimeMessage.setSubject(message.getSubject());
		mimeMessage.setText(message.getMessage());
		
		Transport.send(mimeMessage);
	}

	private Session createSession() {
		Session session = null; 
		Properties properties = Configuration.instance().mailProperties();
		
		if(Configuration.instance().isStarttls()) {
			session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(Configuration.instance().getAccount(), Configuration.instance().getPassword());
					}
				  });
		}
		else {
			session = Session.getDefaultInstance(properties);
		}
		return session;
	}

	
}
