package hu.otp.ticketing.ticket.client;

import hu.otp.ticketing.core.endpoint.rest.OtpCoreApi;
import hu.otp.ticketing.core.endpoint.rest.model.ChargeRequest;
import hu.otp.ticketing.core.endpoint.rest.model.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoreClient {

    private final OtpCoreApi coreApi;

    public Boolean isAuthenticated(String userId, String token) {
        return coreApi.isAuthenticated(userId, token).getInfo().getSuccess();
    }

    public Info charge(String userId, String token, ChargeRequest rq) {
        return coreApi.charge(userId, token, rq).getInfo();
    }
}
