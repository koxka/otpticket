package hu.otp.ticketing.ticket.mapper;

import hu.otp.ticketing.core.endpoint.rest.model.Info;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfoMapper {
    hu.otp.ticketing.ticket.endpoint.rest.model.Info map(Info s);
}
