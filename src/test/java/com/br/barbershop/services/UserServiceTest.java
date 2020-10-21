package com.br.barbershop.services;

import com.br.barbershop.dtos.UserDto;
import com.br.barbershop.factories.UserDtoFactory;
import com.br.barbershop.factories.UserFactory;
import com.br.barbershop.interfaces.UserPersistenciaAdapter;
import com.br.barbershop.models.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

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

        Mockito.when(userPersistenciaAdapter.findByNome(getUser.getNome())).thenReturn(Optional.of(getUser));

        UserDto getUserDto = userService.findByNome(userDto.getNome());

        Assertions.assertThat(getUser.getSenha()).isEqualTo(getUserDto.getSenha());       
        Assertions.assertThat(getUser.getNome()).isEqualTo(getUserDto.getNome());
        Assertions.assertThat(getUser.getEmail()).isEqualTo(getUserDto.getEmail());
    }

    @Test
    @DisplayName("Cria e salva o user")
    public void criarUser() {
        User user = userFactory.getUser();
        UserDto userDto = userDtoFactory.getUserDto();

        Mockito.when(userPersistenciaAdapter.get(userDto.getId())).thenReturn(user);
        Mockito.when(userPersistenciaAdapter.salvar(user)).thenAnswer(AdditionalAnswers.returnsFirstArg());
        
        UserDto getUserDto = userService.salvar(userDto);

        Assertions.assertThat(getUserDto.getNome()).isEqualTo(user.getNome());
        Assertions.assertThat(getUserDto.getSenha()).isEqualTo(user.getSenha());
        Assertions.assertThat(getUserDto.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Encontra user pelo Id")
    public void findUserById() {
        UserDto userDto = userDtoFactory.getUserDto();
        User user = userFactory.getUser();
        
        Mockito.when(userPersistenciaAdapter.get(userDto.getId())).thenReturn(user);

        UserDto getUserDto = userService.get(userDto.getId());

        Assertions.assertThat(getUserDto.getNome()).isEqualTo(user.getNome());
        Assertions.assertThat(getUserDto.getEmail()).isEqualTo(user.getEmail());
        Assertions.assertThat(getUserDto.getSenha()).isEqualTo(user.getSenha());        
    }

    @Test
    @DisplayName("Lista de users")
    public void listaUsers(){
        List<User> listaUsers = new ArrayList<User>();
        User user = userFactory.getUser();
        listaUsers.add(user);

        Mockito.when(userPersistenciaAdapter.getAll()).thenReturn(listaUsers);
        Mockito.when(userPersistenciaAdapter.salvar(user)).thenAnswer(AdditionalAnswers.returnsFirstArg());

        List<UserDto> listaUsersDto = userService.getAll();

        Assertions.assertThat(listaUsersDto).hasSize(1);
        Assertions.assertThat(listaUsersDto.get(0).getNome()).isEqualTo(user.getNome());
        Assertions.assertThat(listaUsersDto.get(0).getEmail()).isEqualTo(user.getEmail());
        Assertions.assertThat(listaUsersDto.get(0).getSenha()).isEqualTo(user.getSenha().toString());
    }
    
}
