package com.google.gwt.phono.test.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.phono.client.Message;
import com.google.gwt.phono.test.client.event.EventBusHelper;
import com.google.gwt.phono.test.client.event.MessageReceivedEvent;
import com.google.gwt.phono.test.client.event.MessageReceivedHandler;
import com.google.gwt.phono.test.client.event.SendMessageEvent;
import com.google.gwt.phono.test.client.event.SendMessageHandler;
import com.google.gwt.phono.test.client.model.Conversation;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MessageList extends Composite {

	private static MessageListUiBinder uiBinder = GWT.create(MessageListUiBinder.class);

	interface MessageListUiBinder extends UiBinder<Widget, MessageList> {
	}

	private final static EventBus EVENT_BUS = EventBusHelper.getEventBus();

	private Conversation conversation;

	@UiField
	VerticalPanel list;

	public MessageList() {
		initWidget(uiBinder.createAndBindUi(this));
		bind();
	}

	private void bind() {
		EVENT_BUS.addHandler(MessageReceivedEvent.getType(), new MessageReceivedHandler() {
			@Override
			public void onMessageReceived(MessageReceivedEvent event) {
				Message message = event.getMessage();
				if(conversation.getWith().equals(message.getFrom())) {
					list.add(new MessageContent(message));
				}
			}
		});
		
		EVENT_BUS.addHandler(SendMessageEvent.getType(), new SendMessageHandler() {
			@Override
			public void onSendMessage(SendMessageEvent event) {
				Conversation conversation = event.getConversation();
				if(MessageList.this.conversation.getWith().equals(conversation.getWith())) {
					list.add(new MessageContent("Moi", event.getBody()));
				}
			}
		});
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

}
