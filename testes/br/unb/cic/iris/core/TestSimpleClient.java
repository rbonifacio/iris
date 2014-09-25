/*
 * TestSimpleClient.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 9, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ExceptionHandling
 *
 */
public class TestSimpleClient {

	private Configuration c;
	
	@Before
	public void setUp() throws Exception {
		//set the server configuration
		c = Configuration.instance();
		
		if(!c.validConfiguraion()) {
			throw new Exception("could not instantiate the configurtion");
		}
	}
	
	@Test
	public void testSendMessage() {
		try {
			(new SimpleClient()).send(new EmailMessage("irismailclient@gmail.com", "irismailclient@gmail.com", "teste", "teste"));
			org.junit.Assert.assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			org.junit.Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testReadMessages() {
		try {
			List<EmailMessage> messages = (new SimpleClient().getMessages(1));
			org.junit.Assert.assertTrue(messages.size() > 0);
		}
		catch(Exception e) {
			e.printStackTrace();
			org.junit.Assert.fail(e.getMessage());
		}
		
	}

}
