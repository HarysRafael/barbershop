package com.br.barbershop.controllers;

import java.util.List;

import com.br.barbershop.dtos.user.UserDto;
import com.br.barbershop.services.user.interfaces.CasoDeUso;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/barbershop")
public class UserController {

    private final CasoDeUso casoDeUso;

    public UserController(CasoDeUso casoDeUso){
        this.casoDeUso = casoDeUso;
    }
    
    @PostMapping("/create")
    private ResponseEntity <UserDto> create(@RequestBody UserDto userDto){
        UserDto user = casoDeUso.salvar(userDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit")
    private ResponseEntity <UserDto> edit(@RequestBody UserDto userDto){
        UserDto user = casoDeUso.editar(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDto> get(@PathVariable Long id){
        UserDto user = casoDeUso.get(id);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/getall")
    private List<UserDto> getAll(){
        return casoDeUso.getAll();
    }

    @DeleteMapping("/{id}")
    private void desativar(@PathVariable Long id){
        casoDeUso.desativar(id);
    }

    @GetMapping()
    private ResponseEntity<UserDto> loadUserByUserName(@RequestParam(value = "username") String username){
        UserDto user = casoDeUso.findByUsername(username);
        return ResponseEntity.ok(user);
    }
        
}   