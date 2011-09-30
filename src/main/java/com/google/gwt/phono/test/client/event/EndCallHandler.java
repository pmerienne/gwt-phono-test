package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EndCallHandler extends EventHandler {

	void onEndCall(EndCallEvent event);
}
