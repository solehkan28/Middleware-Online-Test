package com.sml.model;

public class DTOResult {
	
	private String result;
	private String messages;
	
	public DTOResult(String result,String message){
		this.result = result;
		this.messages = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

}
