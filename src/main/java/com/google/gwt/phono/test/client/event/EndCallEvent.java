package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.phono.test.client.model.Conversation;

public class EndCallEvent extends GwtEvent<EndCallHandler> {

	private static Type<EndCallHandler> TYPE;

	public static Type<EndCallHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<EndCallHandler>());
	}


	private Conversation conversation;

	public EndCallEvent(Conversation conversation) {
		this.conversation = conversation;
	}

	@Override
	protected void dispatch(EndCallHandler handler) {
		handler.onEndCall(this);
	}

	@Override
	public GwtEvent.Type<EndCallHandler> getAssociatedType() {
		return getType();
	}

	public Conversation getConversation() {
		return conversation;
	}

}