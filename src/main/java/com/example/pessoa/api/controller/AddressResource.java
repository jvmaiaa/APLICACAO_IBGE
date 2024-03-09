package com.example.pessoa.api.controller;

import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.dto.response.AddressResponse;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import com.example.pessoa.api.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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
    @ResponseStatus(OK)
    public List<AddressResponse> getAllAddres(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public AddressResponse findById(@PathVariable Long id){
        // Usamos o tipo OPTIONAL quando temos algum objeto que pode NÃO VIR
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public AddressResponse insertAddress(@RequestBody @Valid AddressRequest obj) {
        return service.insert(obj);
    }


    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public AddressResponse updateAddress(@PathVariable Long id, @RequestBody AddressRequest dto) {
       return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        service.delete(id);
    }
}
