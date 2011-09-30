package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.phono.test.client.model.Conversation;

public class ConversationEndedEvent extends GwtEvent<ConversationEndedHandler> {

	private static Type<ConversationEndedHandler> TYPE;

	public static Type<ConversationEndedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<ConversationEndedHandler>());
	}

	private Conversation conversation;

	public ConversationEndedEvent(Conversation conversation) {
		this.conversation = conversation;
	}

	@Override
	protected void dispatch(ConversationEndedHandler handler) {
		handler.onConversationEnded(this);
	}

	@Override
	public GwtEvent.Type<ConversationEndedHandler> getAssociatedType() {
		return getType();
	}

	public Conversation getConversation() {
		return conversation;
	}

}