package com.br.barbershop.factories;

import com.br.barbershop.dtos.UserDto;

public class UserDtoFactory {

    public static UserDto getUserDto() {
        return UserDto.builder()    
            .id(1L)
            .email("email")
            .senha("senha")
            .nome("nome")
            .ativo(true)
            .build();

    }

}
