package hu.otp.ticketing.ticket.endpoint;

import hu.otp.ticketing.ticket.common.exception.MissingHeaderException;
import hu.otp.ticketing.ticket.endpoint.rest.EventsApi;
import hu.otp.ticketing.ticket.endpoint.rest.model.*;
import hu.otp.ticketing.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

import static hu.otp.ticketing.ticket.common.HeaderConstants.TOKEN;
import static hu.otp.ticketing.ticket.common.HeaderConstants.USER_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.basePath}")
public class TicketEndpoint implements EventsApi {
    private final TicketService ticketService;
    private final HttpServletRequest rq;

    @Override
    public ResponseEntity<EventsDetailsResponse> getEvent(BigDecimal eventId) {
        return ResponseEntity.ok(ticketService.getEvent(eventId));
    }

    @Override
    public ResponseEntity<EventsResponse> getEvents() {
        return ResponseEntity.ok(ticketService.getEvents());
    }

    @Override
    public ResponseEntity<ReservationResponse> reserve(@Valid ReservationRequest reservationRequest) {
        return ResponseEntity.ok(ticketService.reserve(rq.getHeader(USER_ID), rq.getHeader(TOKEN), reservationRequest));
    }

    @Override
    public ResponseEntity<DetailedEventDetailsResponse> getDetailedEvent(BigDecimal eventId) {
        return ResponseEntity.ok(ticketService.getEventDetailed(eventId));
    }
}
