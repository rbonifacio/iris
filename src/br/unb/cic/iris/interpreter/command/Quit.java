package br.unb.cic.iris.interpreter.command;

import br.unb.cic.iris.interpreter.i18n.Message;


public class Quit implements MailCommand {

	@Override
	public void execute() {
		System.out.println(Message.instance().getMessage("quit"));
		System.exit(0);
	}

	@Override
	public void explain() {
		System.out.println("(quit) Exit this program");
	}

}
