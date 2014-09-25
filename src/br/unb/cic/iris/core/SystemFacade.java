/*
 * SystemFacade.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 18, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.unb.cic.iris.core.db.IEmailDAO;
import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.persistence.sqlite3.EmailDAO;

/**
 * The project system facade. This class provides methods that should be used by
 * the (possible different) UI component(s). Indeed, it is recommended that all
 * interactions between the UI packages and the core package should occurs
 * through this class.
 * 
 * @author rbonifacio
 */
public class SystemFacade {

	private EmailClient client;

	public SystemFacade() {
		client = new SimpleClient();
		// client = new SilentEmailClient();
	}

	public void send(EmailMessage message) throws EmailException {
		try {
			Configuration c = Configuration.instance();

			if (c == null) {
				throw new EmailException("could not get the configuration data");
			}

			client.send(message);

			IEmailDAO dao = EmailDAO.instance();

			dao.saveMessage(message);
		} catch (EmailException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE,
					"could not send the e-mail message");
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<EmailMessage> retrieveMessages(int sequence) {
		Configuration c = Configuration.instance();

		if (!c.validConfiguraion()) {
			// loadAllProperties(c);
		}

		IEmailDAO dao = EmailDAO.instance();
		// TODO: I have to implement this method.
		// TODO: In addition, it is important to support different accounts
		// within the database.
		// int sequence = dao.lastIncomeMessage();
		return null;
		// return client.getMessages(sequence);
	}

}
