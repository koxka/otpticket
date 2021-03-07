package hu.otp.ticketing.ticket.mapper;

import hu.otp.ticketing.ticket.endpoint.rest.model.EventsDetailsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventsDetailsResponseMapper {
    EventsDetailsResponse map(hu.otp.ticketing.partner.endpoint.rest.model.EventsDetailsResponse source);
}
