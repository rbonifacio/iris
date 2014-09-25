/*
 * TestSystemFacade.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 18, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import org.junit.Assert;

import org.junit.Test;

/**
 * @author ExceptionHandling
 *
 */
public class TestSystemFacade {

	@Test
	public void testSendMessage() {
		try {
			EmailMessage message = new EmailMessage("irismailclient@gmail.com", "irismailclient@gmail.com", "teste", "teste");
			SystemFacade facade = new SystemFacade();
			facade.send(message);
			Assert.assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
