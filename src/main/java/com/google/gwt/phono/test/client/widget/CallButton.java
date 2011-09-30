package com.google.gwt.phono.test.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.phono.test.client.event.EndCallEvent;
import com.google.gwt.phono.test.client.event.EventBusHelper;
import com.google.gwt.phono.test.client.event.StartCallEvent;
import com.google.gwt.phono.test.client.model.Conversation;
import com.google.gwt.user.client.ui.Button;

public class CallButton extends Button {

	private final static EventBus EVENT_BUS = EventBusHelper.getEventBus();

	private Conversation conversation;

	boolean calling;

	public CallButton() {
		super("Appeler");
		calling = false;

		this.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(calling) {
					hangup();
				} else {
					call();
				}
			}
		});
	}

	private void call() {
		this.setText("Racrocher");
		calling = true;
		EVENT_BUS.fireEvent(new StartCallEvent(this.conversation));
	}

	private void hangup() {
		this.setText("Appeler");
		calling = false;
		EVENT_BUS.fireEvent(new EndCallEvent(this.conversation));
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

}
