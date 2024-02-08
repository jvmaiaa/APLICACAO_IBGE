package com.example.pessoa.api.controller;

import com.example.pessoa.api.dto.response.AddressResponse;
import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import com.example.pessoa.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2")
public class AddressResource {

    @Autowired
    private AddressRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddres(){
        List<AddressResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id){
        // Usamos o tipo OPTIONAL quando temos algum objeto que pode NÃO VIR
        Address address = service.findById(id);
        if (address != null) {
            return ResponseEntity.ok().body(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Address> insertAddress(@RequestBody AddressRequest obj) {
        try {
            Address endereco = service.insert(obj);
            return ResponseEntity.ok().body(endereco);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mensagem de erro personalizada", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressRequest> updateAddress(@PathVariable Long id, @RequestBody AddressRequest dto) {
        service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
