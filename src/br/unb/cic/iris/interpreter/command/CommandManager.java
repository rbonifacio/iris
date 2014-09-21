/*---------------------------------------
 * CommandManager
 *--------------------------------------- 
 *  version: 0.0.1 
 *  date: September, 2014
 *  author(s): rbonifacio 
 *  List of changes: (none)
 */
package br.unb.cic.iris.interpreter.command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * A simple class for managing mail commands. 
 * 
 * @author rbonifacio
 */
public class CommandManager {
	
	Properties properties;
	
	private static CommandManager singleton;
	
	private CommandManager() { 
		try { 
			InputStream stream = new FileInputStream("CommandList.properties");
			properties = new Properties();
			properties.load(stream);
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not read the list of commands");
		}
	}
	
	public static CommandManager singleton() {
		if(singleton == null) {
			singleton = new CommandManager();
		}
		return singleton;
	}
	
	public void runCommand(String cmd) {
		String commandName = null;
		try {
			commandName = properties.getProperty(cmd);
			Class<?> c = Class.forName(commandName);
			MailCommand command = (MailCommand)c.newInstance();
			command.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Retrieves the list of commands. 
	 * 
	 * @return the list of commands. 
	 */
	public List<MailCommand> listAll() {
		Set<Object> keys = properties.keySet();
		List<MailCommand> commands = new ArrayList<MailCommand>();
		
		for (Object object : keys) {
			try {
				Class<?> c = Class.forName(properties.getProperty(object.toString()));
				commands.add((MailCommand)c.newInstance());
			}
			catch(ClassNotFoundException e) {
				throw new RuntimeException("Could not find class " + object.toString());
			}
			catch(ClassCastException e) {
				throw new RuntimeException("Class " + object.toString() + " is not a command");
			}
			catch(Exception e) {
				throw new RuntimeException("Could not instantiate class " + object.toString());
			}
		}
		return commands;
	}
}
