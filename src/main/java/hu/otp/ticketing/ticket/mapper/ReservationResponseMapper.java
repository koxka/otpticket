package hu.otp.ticketing.ticket.mapper;

import hu.otp.ticketing.ticket.endpoint.rest.model.ReservationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationResponseMapper {
    ReservationResponse map(hu.otp.ticketing.partner.endpoint.rest.model.ReservationResponse source);
}
