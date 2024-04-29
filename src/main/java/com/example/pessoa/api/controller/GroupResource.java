package com.example.pessoa.api.controller;

import com.example.pessoa.api.entity.Group;
import com.example.pessoa.api.repository.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupResource {

    private final GroupRepository repository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Group> salvar(@RequestBody Group group){
        repository.save(group);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Group>> listar(){
        return ResponseEntity.ok(repository.findAll());
    }
}
