package com.br.barbershop.config.security;

import com.br.barbershop.interfaces.UserPersistenciaAdapter;
import com.br.barbershop.models.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class CustomUserDetail implements UserDetailsService {
    
    private final UserPersistenciaAdapter userPersistenciaAdapter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPersistenciaAdapter.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getSenha(), emptyList());
    }
}