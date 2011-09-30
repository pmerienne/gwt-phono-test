package com.google.gwt.phono.test.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.phono.test.client.event.EventBusHelper;
import com.google.gwt.phono.test.client.event.SendMessageEvent;
import com.google.gwt.phono.test.client.model.Conversation;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MessageEditor extends Composite {

	private static MessageEditorUiBinder uiBinder = GWT.create(MessageEditorUiBinder.class);

	interface MessageEditorUiBinder extends UiBinder<Widget, MessageEditor> {
	}

	private final static EventBus EVENT_BUS = EventBusHelper.getEventBus();

	private Conversation conversation;

	@UiField
	TextBox body;

	@UiField
	CallButton callButton;

	public MessageEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("body")
	protected void onKeyDown(KeyDownEvent event) {
		if (KeyCodes.KEY_ENTER == event.getNativeKeyCode()) {
			send();
		}
	}

	private void send() {
		EVENT_BUS.fireEvent(new SendMessageEvent(conversation, body.getText()));
		body.setText("");
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
		this.callButton.setConversation(conversation);
	}
}
