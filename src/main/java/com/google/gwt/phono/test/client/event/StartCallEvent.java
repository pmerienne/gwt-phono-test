package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.phono.test.client.model.Conversation;

public class StartCallEvent extends GwtEvent<StartCallHandler> {

	private static Type<StartCallHandler> TYPE;

	public static Type<StartCallHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<StartCallHandler>());
	}

	private Conversation conversation;

	public StartCallEvent(Conversation conversation) {
		this.conversation = conversation;
	}

	@Override
	protected void dispatch(StartCallHandler handler) {
		handler.onStartCall(this);
	}

	@Override
	public GwtEvent.Type<StartCallHandler> getAssociatedType() {
		return getType();
	}

	public Conversation getConversation() {
		return conversation;
	}

}