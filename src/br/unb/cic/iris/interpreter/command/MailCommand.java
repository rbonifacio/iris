/*
 * MailCommand
 *--------------------------------------- 
 *  version: 0.0.1 
 *  date: September, 2014
 *  author: rbonifacio
 *  
 *  changes: (none)  
 */
package br.unb.cic.iris.interpreter.command;

/**
 * An interface for processing mail commands. 
 * 
 * @author rbonifacio
 */
public interface MailCommand {
	/**
	 * Each command must provide an execute method. 
	 * The execute method will be triggered when the 
	 * uses ask to. 
	 */
	public void execute();
	
	/**
	 * Each command must provide an explain message, 
	 * which should explain its arguments and usage 
	 * data. 
	 */
	public void explain(); 
}
