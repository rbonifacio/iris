/*
 * Email
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 6, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

/**
 * A class that represents an email message.
 * 
 * @author ExceptionHandling
 */
public class EmailMessage extends FolderContent {
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String message;

	public EmailMessage() {} 
	
	public EmailMessage(String from, String to, String subject, String message) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.message = message; 
	}
	
	public EmailMessage(String from, String to, String cc, String bcc, String subject, String message) {
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.message = message;
	}

	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

}
