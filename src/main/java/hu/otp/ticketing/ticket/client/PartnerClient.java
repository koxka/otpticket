package hu.otp.ticketing.ticket.client;

import hu.otp.ticketing.partner.endpoint.rest.EventsApi;
import hu.otp.ticketing.partner.endpoint.rest.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import static hu.otp.ticketing.ticket.common.ErrorCode.ERROR_DURING_PARTNER_CALL;

/**
 * Client for PARTNER module.
 */
@Component
@RequiredArgsConstructor
public class PartnerClient {
    private final EventsApi eventsApi;

    public EventsResponse getEvents() {
        try {
            return eventsApi.getEvents(UUID.randomUUID().toString());
        } catch (Exception e) {
            EventsResponse rp = new EventsResponse();
            rp.setInfo(createUnreachableBackendInfo());
            return rp;
        }
    }

    public EventsDetailsResponse getEvent(BigDecimal eventId) {
        try {
            return eventsApi.getEvent(UUID.randomUUID().toString(), eventId);
        } catch (Exception e) {
            EventsDetailsResponse rp = new EventsDetailsResponse();
            rp.setInfo(createUnreachableBackendInfo());
            return rp;
        }
    }

    public ReservationResponse reserve(BigDecimal eventId, String seatId) {
        ReservationRequest rq = new ReservationRequest();
        rq.setEventId(eventId);
        rq.setSeatId(seatId);
        try {
            return eventsApi.reserve(UUID.randomUUID().toString(), rq);
        } catch (Exception e) {
            ReservationResponse rp = new ReservationResponse();
            rp.setInfo(createUnreachableBackendInfo());
            return rp;
        }
    }

    private Info createUnreachableBackendInfo() {
        Info info = new Info();
        info.setSuccess(false);
        State state = new State();
        state.setReason(ERROR_DURING_PARTNER_CALL.name());
        state.setCode(ERROR_DURING_PARTNER_CALL.getCode());
        info.setState(state);
        return info;
    }
}
