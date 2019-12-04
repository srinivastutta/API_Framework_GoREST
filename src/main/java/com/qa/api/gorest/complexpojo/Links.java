package com.qa.api.gorest.complexpojo;

public class Links {

	private Self self;
	private Edit edit;
	private Avator avator;
	
	public Links(Self self, Edit edit, Avator avator) {
		
		this.self = self;
		this.edit = edit;
		this.avator = avator;
	}
	public Self getSelf() {
		return self;
	}
	public void setSelf(Self self) {
		this.self = self;
	}
	public Edit getEdit() {
		return edit;
	}
	public void setEdit(Edit edit) {
		this.edit = edit;
	}
	public Avator getAvator() {
		return avator;
	}
	public void setAvator(Avator avator) {
		this.avator = avator;
	}


	
	
}
