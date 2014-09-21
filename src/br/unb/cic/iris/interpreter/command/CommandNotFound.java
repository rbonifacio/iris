/*
 * CommandNotFound
 * -------------------------------------
 *  version: 0.0.1
 *  
 *  date: September, 2014
 *  
 *  authors: rbonifacio
 *  
 *  List of changes: (none)
 */
package br.unb.cic.iris.interpreter.command;

/**
 * Exception that must be thrown when the interpreter 
 * could not execute (find) a user command. 
 * 
 * @author ExceptionHandling
 */
public class CommandNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommandNotFound() { }
	
	public CommandNotFound(String msg) {
		super(msg);
	}
	
	public CommandNotFound(Throwable cause) {
		super(cause);
	}
}
