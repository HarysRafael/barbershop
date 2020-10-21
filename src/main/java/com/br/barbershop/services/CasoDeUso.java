package com.br.barbershop.services;

import java.util.List;

import com.br.barbershop.dtos.UserDto;

public interface CasoDeUso {

    UserDto salvar(UserDto user);

    UserDto editar(UserDto user);

    List<UserDto> getAll();

    UserDto get(Long id);

    void desativar(Long id);

    UserDto findByNome(String nome);
    
}
