package com.br.barbershop.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.barbershop.dtos.user.UserDto;
import com.br.barbershop.exceptions.UsuarioInexistenteException;
import com.br.barbershop.services.user.UserService;

@Service
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
    private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsuarioInexistenteException {
		UserDto user = userService.findByUsername(username);
		
		if (user.getUsername().equals(username)) {
			return new User(username, user.getSenha(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Usuário inválido: " + username);
		}
	}
}
