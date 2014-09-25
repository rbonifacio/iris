/*
 * Folder
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 6, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import java.util.ArrayList;
import java.util.List;

/**
 * A domain class that represents email folders. 
 * 
 * @author rbonifacio
 */
public class Folder extends FolderContent {

	private Integer id;
	private String name;
	private List<FolderContent> contents;
	
	public Folder(String name) {
		this.name = name;
		contents = new ArrayList<FolderContent>();
	}
	
	public Folder(Integer id, String name) {
		this(name);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addElement(FolderContent e) {
		contents.add(e);
	}
	
	public List<FolderContent> getContents() {
		return contents;
	}
}
