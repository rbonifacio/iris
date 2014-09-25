/*
 * BaseException.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 18, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core.exception;

/**
 * Classe usada para representar erros que podem 
 * ocorrer no envio / recebimento de excecoes. 
 * 
 * @author rbonifacio
 */
public class EmailException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EmailException(String message) {
		super(message);
	}
	
	public EmailException(String message, Exception error) {
		super(message, error);
	}
}
