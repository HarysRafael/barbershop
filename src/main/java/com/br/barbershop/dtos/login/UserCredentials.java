package com.br.barbershop.dtos.login;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCredentials {

    String username;
    String senha;
        
}
