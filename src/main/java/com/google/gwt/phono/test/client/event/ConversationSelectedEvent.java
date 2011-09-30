package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.phono.test.client.model.Conversation;

public class ConversationSelectedEvent  extends GwtEvent<ConversationSelectedHandler> {

	private static Type<ConversationSelectedHandler> TYPE;

	public static Type<ConversationSelectedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<ConversationSelectedHandler>());
	}

	private Conversation conversation;

	public ConversationSelectedEvent(Conversation conversation) {
		this.conversation = conversation;
	}

	@Override
	protected void dispatch(ConversationSelectedHandler handler) {
		handler.onConversationSelected(this);
	}

	@Override
	public GwtEvent.Type<ConversationSelectedHandler> getAssociatedType() {
		return getType();
	}

	public Conversation getConversation() {
		return conversation;
	}

}