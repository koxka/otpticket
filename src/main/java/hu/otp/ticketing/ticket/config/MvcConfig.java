package hu.otp.ticketing.ticket.config;

import hu.otp.ticketing.ticket.client.CoreClient;
import hu.otp.ticketing.ticket.security.HeaderValidatorInterceptor;
import hu.otp.ticketing.ticket.security.UserAuthCheckerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    private final HeaderValidatorInterceptor headerValidatorInterceptor;

    private final CoreClient coreClient;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerValidatorInterceptor).addPathPatterns("/**");
        registry.addInterceptor(new UserAuthCheckerInterceptor(coreClient)).addPathPatterns("/**");
    }
}
