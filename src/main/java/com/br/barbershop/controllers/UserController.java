package com.br.barbershop.controllers;

import java.util.List;

import com.br.barbershop.dtos.UserDto;
import com.br.barbershop.services.CasoDeUso;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/barbershop/user")
@RequiredArgsConstructor
public class UserController {

    private final CasoDeUso casoDeUso;

    @PostMapping("/create")
    private UserDto create(@RequestBody UserDto userDto){
        UserDto user = casoDeUso.salvar(userDto);
        return user;
    }

    @PutMapping("/edit")
    private UserDto edit(@RequestBody UserDto userDto){
        UserDto user = casoDeUso.editar(userDto);
        return user;
    }

    @GetMapping("/{id}")
    private UserDto get(@PathVariable Long id){
        UserDto user = casoDeUso.get(id);
        return user;
    }
    
    @GetMapping()
    private List<UserDto> getAll(){
        return casoDeUso.getAll();
    }

    @DeleteMapping("/{id}")
    private void desativar(@PathVariable Long id){
        casoDeUso.desativar(id);
    }

    @GetMapping("/nome/{name}")
    private UserDto getByNome(@RequestParam("name") String name){
        UserDto user = casoDeUso.findByNome(name);
        return user;
    }
        
}
