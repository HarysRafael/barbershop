package com.br.barbershop.factories;

import com.br.barbershop.models.User;

public class UserFactory {
    
    public static User getUser(){
        return User.builder()
            .email("email")
            .senha("senha")
            .nome("nome")
            .ativo(true)
            .build();        
    }
}
