package com.br.barbershop.interfaces;

import java.util.List;
import java.util.Optional;

import com.br.barbershop.models.User;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenciaAdapter{

    private final UserRepository userRepository;

    public User salvar(User user){
        return userRepository.save(user);
    }

    public void desativar(Long id){
        Optional<User> user = userRepository.findByIdAndAtivoTrue(id);
        user.get().setAtivo(false);
        userRepository.save(user.get());
    }
        public User get(Long id){
        Optional<User> user = userRepository.findByIdAndAtivoTrue(id);
        return user.get();

    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findByNome(String nome){
        return userRepository.findByNomeAndAtivoTrue(nome);
        
    }

}
