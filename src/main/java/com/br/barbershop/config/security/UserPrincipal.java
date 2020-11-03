package com.br.barbershop.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.br.barbershop.models.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final User user;
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority g = new SimpleGrantedAuthority(this.user.getRole().getRole());
        authorities.add(g);
        return authorities;
    }

    
    public String getPassword() {
        return this.user.getSenha();
    }

    
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getAtivo();
    }

    public User getUser() {
        return this.user;
    }
    
}
