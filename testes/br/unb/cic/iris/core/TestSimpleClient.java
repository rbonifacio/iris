/*
 * TestSimpleClient.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 9, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author ExceptionHandling
 *
 */
public class TestSimpleClient {

	@Test
	public void test() {
		try {
			//set the server configuration
			Configuration c = Configuration.instance();
			c.fromProperties("config.properties");
			
			//set the account configurations
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File(c.accountPropertyFile())));
			
			c.setAccount(properties.getProperty(Configuration.MAIL_USER));
			c.setPassword(properties.getProperty(Configuration.MAIL_PASSWORD));
			
			SimpleClient sc = new SimpleClient();
			
			EmailMessage message = new EmailMessage("irismailclient@gmail.com", "irismailclient@gmail.com", "teste", "teste");
			sc.send(message);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}
