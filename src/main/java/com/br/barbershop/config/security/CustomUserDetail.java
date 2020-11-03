package com.br.barbershop.config.security;

import com.br.barbershop.interfaces.UserPersistenciaAdapter;
import com.br.barbershop.models.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetail implements UserDetailsService {
    
    private final UserPersistenciaAdapter userPersistenciaAdapter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
        User user = userPersistenciaAdapter.findByUsername(username);
        
            return new UserPrincipal(user);
        }catch(Exception e){
            throw new UsernameNotFoundException(String.format("User not found for %s", username));
        }
    }
}