/*
 * SendMessage.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 8, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.interpreter.command;

import java.io.Console;

import br.unb.cic.iris.core.Configuration;
import br.unb.cic.iris.interpreter.i18n.Message;

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
		Configuration instance = Configuration.instance();
		instance.fromProperties("config.properties");
		if(instance.getPassword() == null) {
			Console console = System.console();
			if(console == null) {
				System.out.println(Message.instance().getMessage("invalid-terminal"));
				return;
			}
			char[] pwd = console.readPassword(Message.instance().getMessage("account-password") + instance.getAccount() + ": ");
			instance.setPassword(new String(pwd));
		}
		System.out.println("sending message");
	}

	/* (non-Javadoc)
	 * @see br.unb.cic.iris.interpreter.command.MailCommand#explain()
	 */
	@Override
	public void explain() {
		System.out.println("(send) compose and send a new message");	
	}
	
}
