package hu.otp.ticketing.ticket.service;

import hu.otp.ticketing.ticket.client.CoreClient;
import hu.otp.ticketing.ticket.client.PartnerClient;
import hu.otp.ticketing.ticket.endpoint.rest.model.*;
import hu.otp.ticketing.ticket.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static hu.otp.ticketing.ticket.common.ErrorCode.GENERAL_ERROR;
import static hu.otp.ticketing.ticket.common.ErrorCode.SUCCESS;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final PartnerClient partnerClient;
    private final CoreClient coreClient;
    private final EventResponseMapper eventResponseMapper;
    private final EventsDetailsResponseMapper eventsDetailsResponseMapper;
    private final ReservationResponseMapper reservationResponseMapper;
    private final ReservationToChargeMapper reservationToChargeMapper;
    private final InfoMapper infoMapper;

    /**
     * Gets event list from PARTNER module.
     *
     * @return {@link EventsResponse}
     */
    public EventsResponse getEvents() {
        return eventResponseMapper.map(partnerClient.getEvents());
    }

    /**
     * Gets event information from PARTNER module for the given id.
     *
     * @param eventId event id.
     * @return {@link EventsDetailsResponse}
     */
    public EventsDetailsResponse getEvent(BigDecimal eventId) {
        return eventsDetailsResponseMapper.map(partnerClient.getEvent(eventId));
    }

    /**
     * Makes the reservation (charges the card and sends the reservation request to PARTNER).
     *
     * @param userId
     * @param token
     * @param rq     request object with reservation data.
     * @return {@link ReservationResponse}
     */
    public ReservationResponse reserve(String userId, String token, ReservationRequest rq) {
        hu.otp.ticketing.core.endpoint.rest.model.Info chargeInfo = coreClient.charge(userId, token, reservationToChargeMapper.map(rq));
        if (chargeInfo.getSuccess()) {
            return reservationResponseMapper.map(partnerClient.reserve(rq.getEventId(), rq.getSeatId()));
        }
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setInfo(infoMapper.map(chargeInfo));
        return reservationResponse;
    }

    /**
     * Returns full detailed information for an event
     *
     * @param eventId event id
     * @return DeatiledEventDetailsResponse
     */
    public DetailedEventDetailsResponse getEventDetailed(BigDecimal eventId) {
        EventsResponse events = getEvents();
        EventsDetailsResponse eventDetail = getEvent(eventId);

        DetailedEventDetailsResponse rp = new DetailedEventDetailsResponse();
        Optional<Event> event = eventExists(events, eventId);
        Info info = new Info();
        info.setSuccess(events.getInfo().getSuccess() && event.isPresent() && eventDetail.getInfo().getSuccess());
        State state = new State();
        state.setCode(info.getSuccess() ? SUCCESS.getCode() : GENERAL_ERROR.getCode());
        state.setReason(info.getSuccess() ? SUCCESS.name() : GENERAL_ERROR.name());
        info.setState(state);
        rp.setInfo(info);
        rp.setEvent(event.orElse(null));
        rp.setSeats(eventDetail.getSeats());
        return rp;
    }

    private Optional<Event> eventExists(EventsResponse events, BigDecimal eventId) {
        if (events == null || events.getData() == null) return Optional.empty();
        return events.getData().stream().filter(e -> e.getEventId().equals(eventId)).findFirst();
    }
}
