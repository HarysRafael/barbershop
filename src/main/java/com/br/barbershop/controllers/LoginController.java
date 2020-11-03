package com.br.barbershop.controllers;

import javax.servlet.http.HttpServletRequest;

import com.br.barbershop.dtos.login.UserCredentials;
import com.br.barbershop.services.login.interfaces.LoginCasosDeUso;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/barbershop")
@RequiredArgsConstructor
public class LoginController {

    private final LoginCasosDeUso loginCasosDeUso;

    @PostMapping("/login")
    private ResponseEntity<ResponseData> login(@RequestBody UserCredentials cred) {
        try {
            loginCasosDeUso.doLogin(cred);
            ResponseData data = new ResponseData(HttpStatus.OK.value(), null, "Logado com sucesso");
            return ResponseEntity.ok(data);
        } catch (BadCredentialsException e) {
            ResponseData data = new ResponseData(HttpStatus.BAD_REQUEST.value(), null, "Não foi possível logar");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PostMapping("/logout")
    private ResponseEntity<ResponseData> logout(HttpServletRequest request) {
        try {
            loginCasosDeUso.doLogout(request);
            ResponseData data = new ResponseData(HttpStatus.OK.value(), null, "Logout realizado com sucesso");
            return ResponseEntity.ok(data);
        } catch (BadCredentialsException e) {
            ResponseData data = new ResponseData(HttpStatus.BAD_REQUEST.value(), null, "Não foi possível logout");
            return ResponseEntity.badRequest().body(data);
        }
    }
}
