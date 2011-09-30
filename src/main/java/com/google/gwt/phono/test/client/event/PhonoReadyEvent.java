package com.google.gwt.phono.test.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.phono.client.Phono;

public class PhonoReadyEvent extends GwtEvent<PhonoReadyHandler> {

	private static Type<PhonoReadyHandler> TYPE;

	public static Type<PhonoReadyHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<PhonoReadyHandler>());
	}

	private Phono phono;

	public PhonoReadyEvent(Phono phono) {
		this.phono = phono;
	}

	@Override
	protected void dispatch(PhonoReadyHandler handler) {
		handler.onPhonoReady(this);
	}

	@Override
	public GwtEvent.Type<PhonoReadyHandler> getAssociatedType() {
		return getType();
	}

	public Phono getPhono() {
		return phono;
	}

}