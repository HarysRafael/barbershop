package com.br.barbershop.interfaces;

import java.util.Optional;

import com.br.barbershop.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByIdAndAtivoTrue(Long id);
    Optional<User> findByNomeAndAtivoTrue(String nome);
    
}
