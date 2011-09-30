package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.phono.test.client.model.Conversation;

public class NewConversationEvent extends GwtEvent<NewConversationHandler> {

	private static Type<NewConversationHandler> TYPE;

	public static Type<NewConversationHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<NewConversationHandler>());
	}

	private Conversation conversation;

	public NewConversationEvent(Conversation conversation) {
		this.conversation = conversation;
	}

	@Override
	protected void dispatch(NewConversationHandler handler) {
		handler.onNewConversation(this);
	}

	@Override
	public GwtEvent.Type<NewConversationHandler> getAssociatedType() {
		return getType();
	}

	public Conversation getConversation() {
		return conversation;
	}

}