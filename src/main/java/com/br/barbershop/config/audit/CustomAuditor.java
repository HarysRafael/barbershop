package com.br.barbershop.config.audit;

import java.util.Optional;

import com.br.barbershop.config.security.UserPrincipal;
import com.br.barbershop.models.User;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomAuditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.of("ipespe");
        }
        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        return Optional.of(user.getUsername());
    }    
}

