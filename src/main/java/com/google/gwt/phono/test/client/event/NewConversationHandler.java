package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NewConversationHandler extends EventHandler {

	void onNewConversation(NewConversationEvent event);
}
