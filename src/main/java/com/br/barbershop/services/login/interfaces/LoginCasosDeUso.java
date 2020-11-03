package com.br.barbershop.services.login.interfaces;

import javax.servlet.http.HttpServletRequest;

import com.br.barbershop.dtos.login.UserCredentials;

import org.springframework.security.authentication.BadCredentialsException;

public interface LoginCasosDeUso {

    void doLogin(UserCredentials cred) throws BadCredentialsException;

    void doLogout(HttpServletRequest request) throws BadCredentialsException;
    
}
