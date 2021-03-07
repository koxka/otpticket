package hu.otp.ticketing.ticket.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UnauthorizedException extends RuntimeException {
    private final String reason;
}
