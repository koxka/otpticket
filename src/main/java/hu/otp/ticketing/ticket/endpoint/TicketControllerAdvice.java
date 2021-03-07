package hu.otp.ticketing.ticket.endpoint;

import hu.otp.ticketing.core.endpoint.rest.model.UnauthorizedResponse;
import hu.otp.ticketing.ticket.common.exception.MissingHeaderException;
import hu.otp.ticketing.ticket.common.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackageClasses = {TicketEndpoint.class})
public class TicketControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedResponse> handleUnauthorizedException(UnauthorizedException e) {
        UnauthorizedResponse rp = new UnauthorizedResponse();
        rp.setReason(e.getReason());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(rp);
    }

    @ExceptionHandler(MissingHeaderException.class)
    public ResponseEntity<String> handleMissingHeaderException(MissingHeaderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
