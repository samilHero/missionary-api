package com.samill.missionary_backend.configs;

import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareConfig implements AuditorAware<String> {

    private static final String ANONYMOUS_USER = "anonymousUser";

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .map(Authentication::getName)
            .or(() -> Optional.of(ANONYMOUS_USER));
    }
}
