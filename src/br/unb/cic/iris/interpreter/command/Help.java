/*
 * Help
 * -------------------------------------------
 *  version: 0.0.1
 *  date: September, 2014
 *  author(s): rbonifacio
 *  
 *  changes: (none)
 */
package br.unb.cic.iris.interpreter.command;

/**
 * A simple mail command that explains how to use the 
 * other commands. 
 * 
 * @author rbonifacio
 */
public class Help implements MailCommand {

	@Override
	public void execute() {
		for(MailCommand c: CommandManager.singleton().listAll()) {
			c.explain();
		}
	}

	@Override
	public void explain() {
		System.out.println("(help) Explain basic usage of each command");	
	}

}
