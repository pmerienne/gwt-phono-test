package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class EventBusHelper {

	private static EventBus eventBus;

	public static EventBus getEventBus() {
		if (eventBus == null) {
			eventBus = new SimpleEventBus();
		}
		return eventBus;
	}
}
