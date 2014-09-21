package br.unb.cic.iris.interpreter.i18n;

import java.util.ResourceBundle;

public class Message {

	ResourceBundle rb;
	
	private static  Message instance;
	
	public Message() {
		rb = ResourceBundle.getBundle("MessageBundle");
	}
	
	public static Message instance() {
		if(instance == null) {
			instance = new Message();
		}
		return instance;
	}
	
	public String getMessage(String key) {
		return rb.getString(key);
	}
}
