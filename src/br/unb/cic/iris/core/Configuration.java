/*
 * Configuration
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 6, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A class with the configurations for sending and receiving email
 * messages.
 * 
 * 
 * @author rbonifacio
 */
public class Configuration {
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String MAIL_USER = "mail.user";
	public static final String MAIL_PASSWORD = "mail.password";
	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	public static final String MAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
	public static final String MAIL_STORE_PROTOCOL = "mail.store.protocol";
	public static final String MAIL_STORE_HOST = "mail.store.host";
	public static final String MAIL_STORE_FOLDER = "mail.store.folder";

	private String account;
	private String password;
	private String server;
	private String storeProtocol;
	private String storeHost;
	private String storeFolder;
	private int port;
	private boolean auth;
	private boolean starttls;
	
	
	private static Configuration instance;
	
	private Configuration() { }

	public static Configuration instance() {
		if(instance == null) {
			try {
				instance = new Configuration();
			
				instance.fromProperties("config.properties");
			
				Properties properties = new Properties();
				properties.load(new FileInputStream(new File(instance.accountPropertyFile())));
				
				//set the user and password properties
				instance.setAccount(properties.getProperty(Configuration.MAIL_USER));
				instance.setPassword(properties.getProperty(Configuration.MAIL_PASSWORD));
			}
			catch(IOException e) {
				instance = null;
			}
		}
		return instance;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public boolean isStarttls() {
		return starttls;
	}

	public void setStarttls(boolean starttls) {
		this.starttls = starttls;
	}
	
	public String getStoreProtocol() {
		return storeProtocol;
	}

	public void setStoreProtocol(String storeProtocol) {
		this.storeProtocol = storeProtocol;
	}

	public String getStoreHost() {
		return storeHost;
	}

	public void setStoreHost(String storeHost) {
		this.storeHost = storeHost;
	}

	public String accountPropertyFile() {
		return System.getProperty("user.home") + "/.iris/account.properties";
	}
	
	private void fromProperties(String fileName) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(fileName));
			
			auth 	 = properties.getProperty(MAIL_SMTP_AUTH).equals("true");
			starttls = properties.getProperty(MAIL_SMTP_STARTTLS).equals("true");
			server 	 = properties.getProperty(MAIL_SMTP_HOST);
			port 	 = Integer.parseInt(properties.getProperty(MAIL_SMTP_PORT));
			storeProtocol = properties.getProperty(MAIL_STORE_PROTOCOL);
			storeHost = properties.getProperty(MAIL_STORE_HOST);
			storeFolder = properties.getProperty(MAIL_STORE_FOLDER);
			
		}catch(Exception e) {
			throw new RuntimeException("Could not open the configuration file " + fileName);
		}
	}
	
	public Properties mailProperties() {
		Properties properties = new Properties();
		
		properties.setProperty(Configuration.MAIL_SMTP_HOST, Configuration.instance().getServer());
		properties.setProperty(Configuration.MAIL_SMTP_PORT, (new Integer(Configuration.instance().getPort())).toString());
		properties.setProperty(Configuration.MAIL_SMTP_AUTH, (new Boolean(Configuration.instance().isAuth()).toString()));
		properties.setProperty(Configuration.MAIL_SMTP_STARTTLS, (new Boolean(Configuration.instance().isStarttls()).toString()));
		properties.setProperty(Configuration.MAIL_STORE_PROTOCOL, storeProtocol);
		properties.setProperty(Configuration.MAIL_STORE_HOST, storeHost);
		properties.setProperty(Configuration.MAIL_STORE_FOLDER, storeFolder);

		
		properties.setProperty(Configuration.MAIL_USER, Configuration.instance().getAccount());
		properties.setProperty(Configuration.MAIL_PASSWORD, Configuration.instance().getPassword());
		return properties;
	}

	/**
	 * Verifies weather the configuration is valid or not.
	 *  
	 * @return true in the case the configuration is valid; otherwise returns false. 
	 */
	public boolean validConfiguraion() {
		return notEmpty(account) && notEmpty(password) && notEmpty(server) && notEmpty(server);
	}

	private boolean notEmpty(String s) {
		return s != null && !s.isEmpty(); 
	}

	public String getStoreFolder() {
		return storeFolder;
	}

	public void setStoreFolder(String storeFolder) {
		this.storeFolder = storeFolder;
	}	
}
