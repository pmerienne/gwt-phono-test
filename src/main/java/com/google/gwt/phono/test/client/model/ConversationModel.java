package com.google.gwt.phono.test.client.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.phono.client.Call;
import com.google.gwt.phono.client.Message;
import com.google.gwt.phono.client.Phono;
import com.google.gwt.phono.client.event.AnswerEvent;
import com.google.gwt.phono.client.event.ErrorEvent;
import com.google.gwt.phono.client.event.HangupEvent;
import com.google.gwt.phono.client.event.IncomingCallEvent;
import com.google.gwt.phono.client.event.MessageEvent;
import com.google.gwt.phono.client.event.PhonoUnreadyEvent;
import com.google.gwt.phono.client.event.RingEvent;
import com.google.gwt.phono.client.handler.PhonoHandler;
import com.google.gwt.phono.test.client.event.ConversationEndedEvent;
import com.google.gwt.phono.test.client.event.ConversationEndedHandler;
import com.google.gwt.phono.test.client.event.EndCallEvent;
import com.google.gwt.phono.test.client.event.EndCallHandler;
import com.google.gwt.phono.test.client.event.EventBusHelper;
import com.google.gwt.phono.test.client.event.MessageReceivedEvent;
import com.google.gwt.phono.test.client.event.MessageReceivedHandler;
import com.google.gwt.phono.test.client.event.NewConversationEvent;
import com.google.gwt.phono.test.client.event.NewConversationHandler;
import com.google.gwt.phono.test.client.event.PhonoReadyEvent;
import com.google.gwt.phono.test.client.event.SendMessageEvent;
import com.google.gwt.phono.test.client.event.SendMessageHandler;
import com.google.gwt.phono.test.client.event.StartCallEvent;
import com.google.gwt.phono.test.client.event.StartCallHandler;
import com.google.gwt.user.client.Window;

public class ConversationModel {

	private final static String API_KEY = "e97d2b4a846dc8c0c75e1c1ff1de0003";

	private final static EventBus EVENT_BUS = EventBusHelper.getEventBus();

	public Map<String, Conversation> actualConversations;

	private Phono phono;

	public ConversationModel() {
		this.actualConversations = new HashMap<String, Conversation>();
		initPhono();
		bind();
	}

	private void bind() {
		EVENT_BUS.addHandler(MessageReceivedEvent.getType(), new MessageReceivedHandler() {
			@Override
			public void onMessageReceived(MessageReceivedEvent event) {
				Message message = event.getMessage();
				String from = message.getFrom();
				if (!actualConversations.containsKey(from)) {
					Conversation conversation = new Conversation(from);
					conversation.setLastMessage(message);
					EVENT_BUS.fireEvent(new NewConversationEvent(conversation));
				} else {
					actualConversations.get(from).setLastMessage(message);
				}
			}
		});

		EVENT_BUS.addHandler(NewConversationEvent.getType(), new NewConversationHandler() {
			@Override
			public void onNewConversation(NewConversationEvent event) {
				Conversation conversation = event.getConversation();
				actualConversations.put(conversation.getWith(), conversation);
			}
		});

		EVENT_BUS.addHandler(ConversationEndedEvent.getType(), new ConversationEndedHandler() {
			@Override
			public void onConversationEnded(ConversationEndedEvent event) {
				Conversation conversation = event.getConversation();
				// Hangup actual call
				if (conversation.getCall() != null) {
					EVENT_BUS.fireEvent(new EndCallEvent(conversation));
				}
				// Remove conversation
				if (actualConversations.containsKey(conversation.getWith())) {
					actualConversations.remove(conversation.getWith());
				}
			}
		});

		EVENT_BUS.addHandler(EndCallEvent.getType(), new EndCallHandler() {
			@Override
			public void onEndCall(EndCallEvent event) {
				Conversation conversation = actualConversations.get(event.getConversation().getWith());
				if (conversation != null) {
					Call call = conversation.getCall();
					if (call != null) {
						call.hangup();
					}
					conversation.setCall(null);
				}
			}
		});

		EVENT_BUS.addHandler(StartCallEvent.getType(), new StartCallHandler() {
			@Override
			public void onStartCall(StartCallEvent event) {
				Conversation conversation = actualConversations.get(event.getConversation().getWith());
				if (conversation != null) {
					// Hangup if already calling
					Call call = conversation.getCall();
					if (call != null) {
						call.hangup();
					}
					// Call using phono
					Call newCall = phono.dial(conversation.getWith());
					conversation.setCall(newCall);
				}
			}
		});

		EVENT_BUS.addHandler(SendMessageEvent.getType(), new SendMessageHandler() {
			@Override
			public void onSendMessage(SendMessageEvent event) {
				Conversation conversation = event.getConversation();
				if (conversation.getLastMessage() == null) {
					phono.send(conversation.getWith(), event.getBody());
				} else {
					conversation.getLastMessage().reply(event.getBody());
				}
			}
		});
	}

	private void initPhono() {
		phono = new Phono(API_KEY, new PhonoHandler() {

			@Override
			public void onRing(RingEvent event) {
			}

			@Override
			public void onPhonoUnready(PhonoUnreadyEvent event) {
				Window.alert("Phono initialization failed.");
			}

			@Override
			public void onPhonoReady(com.google.gwt.phono.client.event.PhonoReadyEvent event) {
				EVENT_BUS.fireEvent(new PhonoReadyEvent(event.getPhono()));
			}

			@Override
			public void onMessage(MessageEvent event) {
				EVENT_BUS.fireEvent(new MessageReceivedEvent(event.getMessage()));
			}

			@Override
			public void onIncomingCall(IncomingCallEvent event) {
				boolean answer = Window.confirm("Appel, voulez-vous décrocher?");
				if (answer) {
					event.getCall().answer();
				}
			}

			@Override
			public void onHangup(HangupEvent event) {
				Window.alert("Call ended by remote user ");
			}

			@Override
			public void onError(ErrorEvent event) {
				Window.alert("Error : " + event.getReason());
			}

			@Override
			public void onAnswer(AnswerEvent event) {
				Window.alert("Call answered by remote user ");
			}
		});
	}
}
