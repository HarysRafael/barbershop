package com.br.barbershop.services;

import com.br.barbershop.dtos.UserDto;
import com.br.barbershop.interfaces.UserPersistenciaAdapter;
import com.br.barbershop.models.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserService implements CasoDeUso{
    
    private final UserPersistenciaAdapter userPersistenciaAdapter;

    @Override
    public UserDto salvar(UserDto userDto){
        User user = converterDtoEmUser(userDto);
        userPersistenciaAdapter.salvar(user);
        return userDto;

    }

    @Override
    public UserDto editar(UserDto userDto){
        User user = converterDtoEmUser(userDto);
        user = userPersistenciaAdapter.salvar(user);
        user.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto get(Long id){
        User user = userPersistenciaAdapter.get(id);
        UserDto userDto = converterUserEmDto(user);
        return userDto;
    }

    @Override
    public UserDto findByNome(String nome){
       Optional<User> user = userPersistenciaAdapter.findByNome(nome);
        UserDto userDto = converterUserEmDto(user.get());
        return userDto;
    }

    @Override
    public void desativar(Long id){
        userPersistenciaAdapter.desativar(id);
    }

    @Override
    public List<UserDto> getAll() {
        return userPersistenciaAdapter.getAll().stream()
                .map(user -> {
                    return converterUserEmDto(user);
                })
                .collect(Collectors.toList());
}  

    public User converterDtoEmUser(UserDto userDto){
        return User.builder()
                    .email(userDto.getEmail())
                    .nome(userDto.getNome())
                    .senha(userDto.getSenha())
                    .ativo(userDto.getAtivo())
                    .id(userDto.getId())
                    .build();
    }

    public UserDto converterUserEmDto(User user){
        return UserDto.builder()
                    .email(user.getEmail())
                    .nome(user.getNome())
                    .senha(user.getSenha())
                    .id(user.getId())
                    .ativo(user.getAtivo())
                    .build();
    }
}
