package com.google.gwt.phono.test.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.phono.client.Message;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MessageContent extends Composite {

	private static MessageContentUiBinder uiBinder = GWT.create(MessageContentUiBinder.class);

	interface MessageContentUiBinder extends UiBinder<Widget, MessageContent> {
	}

	@UiField
	HeadingElement from;

	@UiField
	ParagraphElement body;

	public MessageContent() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public MessageContent(Message message) {
		this();
		this.from.setInnerText(message.getFrom() + " : ");
		this.body.setInnerHTML(message.getBody());
	}

	public MessageContent(String from, String body) {
		this();
		this.from.setInnerText(from + " : ");
		this.body.setInnerHTML(body);
	}
}
