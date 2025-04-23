package io.madeformaid.webmvc.jpa.audit;

import io.madeformaid.webmvc.context.AuthContext;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class ThreadLocalAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(AuthContext.getUserId());
    }
}
