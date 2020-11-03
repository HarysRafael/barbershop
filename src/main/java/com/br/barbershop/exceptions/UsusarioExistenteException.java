package com.br.barbershop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsusarioExistenteException extends RuntimeException{
    
    public UsusarioExistenteException(String message){
        super(message);

    }
    
}
