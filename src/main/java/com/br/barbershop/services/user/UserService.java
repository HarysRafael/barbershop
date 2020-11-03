package com.br.barbershop.services.user;

import com.br.barbershop.dtos.user.UserDto;
import com.br.barbershop.interfaces.UserPersistenciaAdapter;
import com.br.barbershop.models.User;
import com.br.barbershop.services.user.interfaces.CasoDeUso;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
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
        UserDto userResponse = converterUserEmDto(user);
        return userResponse;

    }

    @Override
    public UserDto editar(UserDto userDto){
        User user = converterDtoEmUser(userDto);
        get(user.getId());
        user = userPersistenciaAdapter.salvarEdicao(user);
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
    public UserDto findByUsername(String username){
       User user = userPersistenciaAdapter.findByUsername(username);
       UserDto userDto = converterUserEmDto(user);
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                    .email(userDto.getEmail())
                    .nome(userDto.getNome())
                    .senha(passwordEncoder.encode(userDto.getSenha()))
                    .ativo(userDto.getAtivo())
                    .role(userDto.getRole())
                    .username(userDto.getUsername())
                    .id(userDto.getId())
                    .build();
    }

    public UserDto converterUserEmDto(User user){
        return UserDto.builder()
                    .email(user.getEmail())
                    .nome(user.getNome())
                    .senha(user.getSenha())
                    .id(user.getId())
                    .role(user.getRole())
                    .username(user.getUsername())
                    .ativo(user.getAtivo())
                    .build();
    }
}
