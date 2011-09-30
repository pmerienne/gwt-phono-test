package com.google.gwt.phono.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.phono.test.client.model.ConversationModel;
import com.google.gwt.phono.test.client.ui.MessagingService;
import com.google.gwt.user.client.ui.RootPanel;

public class PhonoTest implements EntryPoint {

	@Override
	public void onModuleLoad() {
		new ConversationModel();
		RootPanel.get().add(new MessagingService());
	}

}
