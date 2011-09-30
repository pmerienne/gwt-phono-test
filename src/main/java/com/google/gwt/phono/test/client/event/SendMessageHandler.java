package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface SendMessageHandler extends EventHandler {

	void onSendMessage(SendMessageEvent event);
}
