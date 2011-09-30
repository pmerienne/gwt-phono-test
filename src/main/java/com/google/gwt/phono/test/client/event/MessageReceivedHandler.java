package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface MessageReceivedHandler extends EventHandler {

	void onMessageReceived(MessageReceivedEvent event);
}
