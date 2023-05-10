package com.sap.bookingplatform.exception;

public class ShowAlreadyBookedException extends RuntimeException {

	private static final long serialVersionUID = 9097965925786182605L;

	public ShowAlreadyBookedException(String message) {
		super(message);
	}

}
