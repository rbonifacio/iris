/*
 * SilentEmailClient.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 20, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import br.unb.cic.iris.core.exception.EmailException;

/**
 * A silent implementation of an @see br.unb.cic.iris.core.EmailClient
 * This class is only interesting for tessting the application without 
 * the need to send email messages. 
 * 
 * @author rbonifacio
 */
public class SilentEmailClient implements EmailClient {

	@Override
	public void send(EmailMessage message) throws EmailException {
		System.out.println("[silent mode]");
		System.out.print("sending a message [");
		for(int i = 0; i < 10; i++) {
			try {
				wait(500);
			}
			catch(Exception e) {
				System.out.println("could not wait for 0.5 seconds");
				System.out.println("done!");
				break;
			}
			System.out.print(".");
		}
		System.out.println("]");
		System.out.println("done");
	}

}
