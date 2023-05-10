package com.sap.bookingplatform.exception;

public class SeatAlreadyBookedException extends RuntimeException {

    private static final long serialVersionUID = -3715341249599031888L;

    public SeatAlreadyBookedException(String message) {
        super(message);
    }

}
