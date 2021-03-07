package hu.otp.ticketing.ticket.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    SUCCESS(0L),
    GENERAL_ERROR(30000L),
    NO_SUCH_EVENT_OR_COMMUNICATION_ERROR(30001L),
    ERROR_DURING_PARTNER_CALL(30002L);

    private final Long code;

    ErrorCode(long l) {
        this.code = l;
    }
}
