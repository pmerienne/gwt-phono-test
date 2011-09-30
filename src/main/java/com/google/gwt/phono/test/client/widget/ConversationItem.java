package com.google.gwt.phono.test.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.phono.test.client.event.ConversationSelectedEvent;
import com.google.gwt.phono.test.client.event.ConversationSelectedHandler;
import com.google.gwt.phono.test.client.event.EventBusHelper;
import com.google.gwt.phono.test.client.model.Conversation;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ConversationItem extends Composite {

	private static ConversationItemUiBinder uiBinder = GWT.create(ConversationItemUiBinder.class);

	interface ConversationItemUiBinder extends UiBinder<Widget, ConversationItem> {
	}

	private final static EventBus EVENT_BUS = EventBusHelper.getEventBus();

	@UiField
	Label heading;

	private Conversation conversation;

	public ConversationItem(Conversation conversation) {
		initWidget(uiBinder.createAndBindUi(this));

		this.conversation = conversation;
		this.heading.setText(conversation.getWith());
		
		bind();
	}

	private void bind() {
		EVENT_BUS.addHandler(ConversationSelectedEvent.getType(), new ConversationSelectedHandler() {
			@Override
			public void onConversationSelected(ConversationSelectedEvent event) {
				if(event.getConversation().equals(ConversationItem.this.conversation)) {
					heading.addStyleName("active");
				} else {
					heading.removeStyleName("active");
				}
			}
		});
	}

	@UiHandler("heading")
	protected void onClick(ClickEvent event) {
		EVENT_BUS.fireEvent(new ConversationSelectedEvent(conversation));
	}
}
