package com.br.barbershop.services;

import com.br.barbershop.dtos.user.UserDto;
import com.br.barbershop.factories.UserDtoFactory;
import com.br.barbershop.factories.UserFactory;
import com.br.barbershop.interfaces.UserPersistenciaAdapter;
import com.br.barbershop.models.User;
import com.br.barbershop.services.user.UserService;

import java.util.List;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    
    @InjectMocks
    private UserService userService;
    private UserFactory userFactory;
    private UserDtoFactory userDtoFactory;

    @Mock
    private UserPersistenciaAdapter userPersistenciaAdapter;

    @Test
    @DisplayName("Carrega user pelo nome")
    void findByNome() {
        UserDto userDto = userDtoFactory.getUserDto();
        User getUser = userFactory.getUser();

        Mockito.when(userPersistenciaAdapter.findByUsername(getUser.getUsername())).thenReturn(getUser);

        UserDto getUserDto = userService.findByUsername(userDto.getUsername());

        Assertions.assertThat(getUser.getSenha()).isEqualTo(getUserDto.getSenha());       
        Assertions.assertThat(getUser.getNome()).isEqualTo(getUserDto.getNome());
        Assertions.assertThat(getUser.getEmail()).isEqualTo(getUserDto.getEmail());
        Assertions.assertThat(getUser.getUsername()).isEqualTo(getUserDto.getUsername());       
        Assertions.assertThat(getUser.getRole()).isEqualTo(getUserDto.getRole());       
    }

    @Test
    @DisplayName("Cria e salva o user")
    public void criarUser() {
        User getUser = userFactory.getUser();
        UserDto userDto = userDtoFactory.getUserDto();

        Mockito.when(userPersistenciaAdapter.get(userDto.getId())).thenReturn(getUser);
        Mockito.when(userPersistenciaAdapter.salvar(getUser)).thenAnswer(AdditionalAnswers.returnsFirstArg());
        
        UserDto getUserDto = userService.salvar(userDto);

        Assertions.assertThat(getUser.getSenha()).isEqualTo(getUserDto.getSenha());       
        Assertions.assertThat(getUser.getNome()).isEqualTo(getUserDto.getNome());
        Assertions.assertThat(getUser.getEmail()).isEqualTo(getUserDto.getEmail());
        Assertions.assertThat(getUser.getUsername()).isEqualTo(getUserDto.getUsername());       
        Assertions.assertThat(getUser.getRole()).isEqualTo(getUserDto.getRole());
        

    }

    @Test
    @DisplayName("Encontra user pelo Id")
    public void findUserById() {
        UserDto userDto = userDtoFactory.getUserDto();
        User getUser = userFactory.getUser();
        
        Mockito.when(userPersistenciaAdapter.get(userDto.getId())).thenReturn(getUser);

        UserDto getUserDto = userService.get(userDto.getId());

        Assertions.assertThat(getUser.getSenha()).isEqualTo(getUserDto.getSenha());       
        Assertions.assertThat(getUser.getNome()).isEqualTo(getUserDto.getNome());
        Assertions.assertThat(getUser.getEmail()).isEqualTo(getUserDto.getEmail());
        Assertions.assertThat(getUser.getUsername()).isEqualTo(getUserDto.getUsername());       
        Assertions.assertThat(getUser.getRole()).isEqualTo(getUserDto.getRole());
    }

    @Test
    @DisplayName("Lista de users")
    public void listaUsers(){
        List<User> listaUsers = new ArrayList<User>();
        User getUser = userFactory.getUser();
        listaUsers.add(getUser);

        Mockito.when(userPersistenciaAdapter.getAll()).thenReturn(listaUsers);
        Mockito.when(userPersistenciaAdapter.salvar(getUser)).thenAnswer(AdditionalAnswers.returnsFirstArg());

        List<UserDto> listaUsersDto = userService.getAll();

        Assertions.assertThat(listaUsersDto).hasSize(1);
        Assertions.assertThat(listaUsersDto.get(0).getNome()).isEqualTo(getUser.getNome());
        Assertions.assertThat(listaUsersDto.get(0).getEmail()).isEqualTo(getUser.getEmail());
        Assertions.assertThat(listaUsersDto.get(0).getSenha()).isEqualTo(getUser.getSenha().toString());
        Assertions.assertThat(listaUsersDto.get(0).getUsername()).isEqualTo(getUser.getUsername());       
        Assertions.assertThat(listaUsersDto.get(0).getRole()).isEqualTo(getUser.getRole());
    }
    
}
