/*
 * EmailClientImpl.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 6, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
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
	public void send(EmailMessage message)  throws EmailException {
		try {
			Session session = createSession();
			send(message, session);
		}
		catch(Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "erro...");
			throw new EmailException("blahh", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see br.unb.cic.iris.core.EmailClient#getMessages(int)
	 */
	@Override
	public List<EmailMessage> getMessages(int seqnum) throws EmailException {
		Properties properties = Configuration.instance().mailProperties();
		Session session = Session.getDefaultInstance(properties);
		Store store = null;
		try {
			store = session.getStore(Configuration.instance().getStoreProtocol());
			store.connect(Configuration.instance().getStoreHost(), Configuration.instance().getAccount(), Configuration.instance().getPassword());
			Folder inbox = store.getFolder(Configuration.instance().getStoreFolder());
			inbox.open(Folder.READ_ONLY);
			
			int from = seqnum;
			int end  = inbox.getMessageCount();
			
			Message messages [] = inbox.getMessages(from, end);
			
			//----------------------------------------------------------
			//That's so bad. I still miss the lambda expressions of 
			//Java 8.
			List<EmailMessage> result = new ArrayList<EmailMessage>();
			for(Message m : messages) {
				result.add(convertMessageToEmailMessage(m));
			}
			//the end of my complain about the absence of Java 8. 
			//----------------------------------------------------------
	
			return result;
		}
		catch(Exception e) {
			throw new EmailException("Could not get new messages", e);
		}
		finally {
			try {
				if(store != null && store.isConnected()) {
					store.close();
				}
			}catch(Exception e) {
				throw new EmailException("Could not close the email store", e);
			}
			
		}
	}

	/*
	 * Convert a Java Mail message into a EmailMessage. 
	 */
	private EmailMessage convertMessageToEmailMessage(Message m) throws Exception {
		String content = null;
		if(m.getContent() instanceof Multipart) {
			Multipart mp = (Multipart) m.getContent();
        	BodyPart bp = mp.getBodyPart(0);
        	content = bp.getContent().toString();
		}
		else if (m.getContent() instanceof String) {
			content = m.getContent().toString();
		}
		
		EmailMessage result = new EmailMessage();
		
		result.setFrom(convertAddressToString(m.getFrom()));
		result.setTo(convertAddressToString(m.getRecipients(RecipientType.TO)));
		result.setCc(convertAddressToString(m.getRecipients(RecipientType.CC)));
		result.setBcc(convertAddressToString(m.getRecipients(RecipientType.BCC)));
		
		
		//TODO: I've to check weather is safe or not to assume 
		//the <i>body part</i> as a String.
		
		result.setSubject(m.getSubject().toString());
		result.setMessage(content);
		
		return result;
	}
	
	/*
	 * Convert a mail message to a String
	 */
	public String convertAddressToString(Address addresses[]) {
		if(addresses != null && addresses.length > 0) {
			StringBuffer buffer = new StringBuffer();
			
			for(Address a: addresses) {
				buffer.append(a.toString());
				buffer.append(";");
			}
			return buffer.toString();
		}
		return null;
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
