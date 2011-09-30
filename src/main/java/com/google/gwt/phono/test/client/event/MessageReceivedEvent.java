package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.phono.client.Message;

public class MessageReceivedEvent extends GwtEvent<MessageReceivedHandler> {

	private static Type<MessageReceivedHandler> TYPE;

	public static Type<MessageReceivedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<MessageReceivedHandler>());
	}

	private Message message;

	public MessageReceivedEvent(Message message) {
		this.message = message;
	}

	@Override
	protected void dispatch(MessageReceivedHandler handler) {
		handler.onMessageReceived(this);
	}

	@Override
	public GwtEvent.Type<MessageReceivedHandler> getAssociatedType() {
		return getType();
	}

	public Message getMessage() {
		return message;
	}

}