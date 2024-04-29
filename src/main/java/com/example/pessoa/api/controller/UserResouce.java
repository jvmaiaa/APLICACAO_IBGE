package com.example.pessoa.api.controller;


import com.example.pessoa.api.dto.request.UserRegistrationRequestDTO;
import com.example.pessoa.api.entity.User;
import com.example.pessoa.api.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResouce {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> salvar(@RequestBody UserRegistrationRequestDTO body){
        User savedUser = userService.salvar(body.getUser(), body.getPermissions());
        return ResponseEntity.ok(savedUser);
    }
}
