package hu.otp.ticketing.ticket.mapper;

import hu.otp.ticketing.ticket.endpoint.rest.model.EventsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventResponseMapper {
    EventsResponse map(hu.otp.ticketing.partner.endpoint.rest.model.EventsResponse source);
}
