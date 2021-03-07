package hu.otp.ticketing.ticket.common.exception;

public class MissingHeaderException extends RuntimeException {

    public MissingHeaderException(String message) {
        super(message);
    }
}
