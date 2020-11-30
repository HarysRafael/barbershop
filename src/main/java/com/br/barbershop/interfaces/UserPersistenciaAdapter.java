package com.br.barbershop.interfaces;

import java.util.List;

import com.br.barbershop.exceptions.UsuarioInexistenteException;
import com.br.barbershop.exceptions.UsusarioExistenteException;
import com.br.barbershop.models.User;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenciaAdapter{
  
    private final UserRepository userRepository;

    public User salvar(User user){
        verificarDados(user);
        return userRepository.save(user);
    }

    public User salvarEdicao(User user){
        return userRepository.save(user);
    }

    public void desativar(Long id){
        User user = userRepository.findByIdAndAtivoTrue(id).orElseThrow(UsuarioInexistenteException::new);
        user.setAtivo(false);
        userRepository.save(user);
    }

        public User get(Long id){
        User user = userRepository.findByIdAndAtivoTrue(id).orElseThrow(UsuarioInexistenteException::new);
        return user;
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsernameAndAtivoTrue(username).orElseThrow(UsuarioInexistenteException::new);
    }

    public User verificarDados(User user){
        userRepository.findByUsernameAndAtivoTrue(user.getNome()).ifPresent(user1 -> {
            throw new UsusarioExistenteException("Usuário já existe.");
        });
        userRepository.findByEmailAndAtivoTrue(user.getEmail()).ifPresent(user1 -> {
            throw new UsusarioExistenteException("Usuário já existe.");
        });
        return user;
    }
}
