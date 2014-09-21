/*
 * EmailCliente
 * --------------------------------------
 *  version: 0.0.1
 *  date: September, 2014
 *  author: rbonifacio
 *  list of changes: (none)
 */
package br.unb.cic.iris.core;

import br.unb.cic.iris.core.exception.EmailException;

public interface EmailClient {

	public void send(EmailMessage message) throws EmailException;
}
