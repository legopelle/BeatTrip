package com.beardedwhale.library.Dialog;

public abstract interface DialogListener {

	public abstract void onClose(Dialog D);
	
	public abstract void onEvent(Dialog D, String eventId);
	
}
