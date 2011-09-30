package com.google.gwt.phono.test.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.phono.test.client.event.ConversationEndedEvent;
import com.google.gwt.phono.test.client.event.ConversationEndedHandler;
import com.google.gwt.phono.test.client.event.ConversationSelectedEvent;
import com.google.gwt.phono.test.client.event.ConversationSelectedHandler;
import com.google.gwt.phono.test.client.event.EventBusHelper;
import com.google.gwt.phono.test.client.event.NewConversationEvent;
import com.google.gwt.phono.test.client.event.NewConversationHandler;
import com.google.gwt.phono.test.client.event.PhonoReadyEvent;
import com.google.gwt.phono.test.client.event.PhonoReadyHandler;
import com.google.gwt.phono.test.client.model.Conversation;
import com.google.gwt.phono.test.client.widget.ConversationItem;
import com.google.gwt.phono.test.client.widget.ConversationPanel;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MessagingService extends Composite {

	private static MessagingServiceUiBinder uiBinder = GWT.create(MessagingServiceUiBinder.class);

	interface MessagingServiceUiBinder extends UiBinder<Widget, MessagingService> {
	}

	private final static EventBus EVENT_BUS = EventBusHelper.getEventBus();

	private Map<String, ConversationPanel> tabs = new HashMap<String, ConversationPanel>();

	@UiField
	Label status;

	@UiField
	TextBox with;
	
	@UiField
	VerticalPanel conversationList;

	@UiField
	FlowPanel conversationPanel;
	
	public MessagingService() {
		initWidget(uiBinder.createAndBindUi(this));
		bind();
	}

	private void bind() {
		EVENT_BUS.addHandler(PhonoReadyEvent.getType(), new PhonoReadyHandler() {
			@Override
			public void onPhonoReady(PhonoReadyEvent event) {
				status.setText("Phono prêt, id : " + event.getPhono().getSessionId());
			}
		});

		EVENT_BUS.addHandler(NewConversationEvent.getType(), new NewConversationHandler() {
			@Override
			public void onNewConversation(NewConversationEvent event) {
				Conversation conversation = event.getConversation();
				ConversationPanel panel = new ConversationPanel(conversation);
				conversationList.add(new ConversationItem(conversation));
				conversationPanel.clear();
				conversationPanel.add(panel);
				tabs.put(conversation.getWith(), panel);
			}
		});
		
		EVENT_BUS.addHandler(ConversationSelectedEvent.getType(), new ConversationSelectedHandler() {
			@Override
			public void onConversationSelected(ConversationSelectedEvent event) {
				ConversationPanel panel = tabs.get(event.getConversation().getWith());
				conversationPanel.clear();
				conversationPanel.add(panel);
			}
		});

		EVENT_BUS.addHandler(ConversationEndedEvent.getType(), new ConversationEndedHandler() {
			@Override
			public void onConversationEnded(ConversationEndedEvent event) {
				ConversationPanel panel = tabs.get(event.getConversation().getWith());
				if (panel != null) {
					//TODO cas où c'est le panneau affiché
				}
			}
		});
	}

	@UiHandler("startConversationButton")
	protected void onStartConversation(ClickEvent event) {
		Conversation conversation = new Conversation(with.getText());
		EVENT_BUS.fireEvent(new NewConversationEvent(conversation));
	}
}
