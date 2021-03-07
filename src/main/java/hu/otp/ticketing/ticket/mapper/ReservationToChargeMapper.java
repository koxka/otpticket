package hu.otp.ticketing.ticket.mapper;

import hu.otp.ticketing.core.endpoint.rest.model.ChargeRequest;
import hu.otp.ticketing.ticket.endpoint.rest.model.ReservationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationToChargeMapper {
    ChargeRequest map(ReservationRequest s);
}
