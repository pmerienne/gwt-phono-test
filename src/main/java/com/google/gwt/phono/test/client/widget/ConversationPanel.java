package com.google.gwt.phono.test.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.phono.test.client.model.Conversation;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ConversationPanel extends Composite {

	private static ConversationPanelUiBinder uiBinder = GWT.create(ConversationPanelUiBinder.class);

	interface ConversationPanelUiBinder extends UiBinder<Widget, ConversationPanel> {
	}

	@UiField
	MessageList messageList;

	@UiField
	MessageEditor messageEditor;

	public ConversationPanel(Conversation conversation) {
		initWidget(uiBinder.createAndBindUi(this));
		this.messageList.setConversation(conversation);
		this.messageEditor.setConversation(conversation);
	}

}
