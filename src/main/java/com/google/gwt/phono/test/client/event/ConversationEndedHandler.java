package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ConversationEndedHandler extends EventHandler {

	void onConversationEnded(ConversationEndedEvent event);
}
