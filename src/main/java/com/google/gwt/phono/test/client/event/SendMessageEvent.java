package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.phono.test.client.model.Conversation;

public class SendMessageEvent extends GwtEvent<SendMessageHandler> {

	private static Type<SendMessageHandler> TYPE;

	public static Type<SendMessageHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<SendMessageHandler>());
	}

	private Conversation conversation;

	private String body;

	public SendMessageEvent(Conversation conversation, String body) {
		this.conversation = conversation;
		this.body = body;
	}

	@Override
	protected void dispatch(SendMessageHandler handler) {
		handler.onSendMessage(this);
	}

	@Override
	public GwtEvent.Type<SendMessageHandler> getAssociatedType() {
		return getType();
	}

	public Conversation getConversation() {
		return conversation;
	}

	public String getBody() {
		return body;
	}

}