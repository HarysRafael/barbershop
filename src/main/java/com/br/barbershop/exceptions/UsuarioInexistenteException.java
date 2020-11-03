package com.br.barbershop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsuarioInexistenteException extends RuntimeException{
    
    public UsuarioInexistenteException(String message){
        super(message);

    }

    public UsuarioInexistenteException(){

    }

}
