package com.br.barbershop.dtos.user;

import com.br.barbershop.models.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Role role;
    private String username;
    private Boolean ativo=true;
    
}
