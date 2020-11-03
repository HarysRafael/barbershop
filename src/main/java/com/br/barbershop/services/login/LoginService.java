package com.br.barbershop.services.login;

import javax.servlet.http.HttpServletRequest;

import com.br.barbershop.dtos.login.UserCredentials;
import com.br.barbershop.services.login.interfaces.LoginCasosDeUso;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginService implements LoginCasosDeUso{

    private final AuthenticationManager authManager;

    @Override
    public void doLogin(UserCredentials cred) throws BadCredentialsException {
        try {
            UsernamePasswordAuthenticationToken authenticationTokenRequest = new UsernamePasswordAuthenticationToken(
                    cred.getUsername(), cred.getSenha());
            Authentication authentication = authManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            log.warn("Bad credentials", e);
            throw e;
        }    
    }

    @Override
    public void doLogout(HttpServletRequest request) {
        try {
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.logout(request, null, null);
        } catch (Exception e) {
            throw e;
        }
    }
}
