package com.br.barbershop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer; 
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.inMemory()
            .withClient("barbershop-web")
                .secret(passwordEncoder.encode("123"))
                .authorizedGrantTypes("password")
                .scopes("write","read")
                .accessTokenValiditySeconds(600)
                .and()
            .withClient("barbershop-mobile")
                .secret(passwordEncoder.encode("321"))
                .authorizedGrantTypes("password", "otherGrantType")
                .scopes("write","read")
                .accessTokenValiditySeconds(600);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .authenticationManager(authenticationManager);
    }

    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
        security.checkTokenAccess("isAuthenticated()");
    }
        
    }