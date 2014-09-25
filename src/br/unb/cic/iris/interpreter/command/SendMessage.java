/*
 * SendMessage.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 8, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.interpreter.command;

import java.util.Scanner;

import br.unb.cic.iris.core.EmailMessage;
import br.unb.cic.iris.core.SystemFacade;

/**
 * 
 * @author rbonifacio
 */
public class SendMessage implements MailCommand {

	/* (non-Javadoc)
	 * @see br.unb.cic.iris.interpreter.command.MailCommand#execute()
	 */
	@Override
	public void execute() {
		String from, to, subject, content;
		Scanner sc = new Scanner(System.in);
	
		System.out.println("From: " );
		from = sc.nextLine();
		
		System.out.println("To: ");
		to = sc.nextLine();
		
		System.out.println("Subject: ");
		subject = sc.nextLine();
		
		System.out.println("Content: ");
		content = sc.nextLine();
		
		EmailMessage m = new EmailMessage(from, to, null, null, subject, content);
		
		SystemFacade facade = new SystemFacade();
		try {
			facade.send(m);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see br.unb.cic.iris.interpreter.command.MailCommand#explain()
	 */
	@Override
	public void explain() {
		System.out.println("(send) compose and send a new message");	
	}
	
}
