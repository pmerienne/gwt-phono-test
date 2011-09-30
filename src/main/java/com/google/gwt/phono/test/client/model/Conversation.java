package com.google.gwt.phono.test.client.model;

import com.google.gwt.phono.client.Call;
import com.google.gwt.phono.client.Message;

public class Conversation {

	private String with;

	private Call call;

	private Message lastMessage;

	public Conversation() {
	}

	public Conversation(String with) {
		this.with = with;
	}

	public String getWith() {
		return with;
	}

	public void setWith(String with) {
		this.with = with;
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public Message getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(Message lastMessage) {
		this.lastMessage = lastMessage;
	}

}
