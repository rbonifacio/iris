/*
 * Account.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 25, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

/**
 * A business class for representing 
 * email accounts. 
 * 
 * @author rbonifacio
 */
public class Account {
	public Integer id;
	public String account;

	public Account() {
	}

	public Account(Integer id, String account) {
		this.id = id;
		this.account = account;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
