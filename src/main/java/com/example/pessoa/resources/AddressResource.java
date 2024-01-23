package com.example.pessoa.resources;

import com.example.pessoa.DTOs.RequestAddressDTO;
import com.example.pessoa.domain.Address;
import com.example.pessoa.repositories.AddressRepository;
import com.example.pessoa.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/v2")
public class AddressResource {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddres(){
        var allAddress = repository.findAll();
        return ResponseEntity.ok(allAddress);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id){
        // Usamos o tipo OPTIONAL quando temos algum objeto que pode NÃO VIR
        Optional<Address> obj = repository.findById(id);
        if (obj.isPresent()) {
            return ResponseEntity.ok().body(obj.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Address> insertAddress(@RequestBody RequestAddressDTO obj) {
        Address newAddress = new Address(obj);
        repository.save(newAddress);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").
                buildAndExpand(newAddress.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestAddressDTO> updateAddress(@PathVariable Long id, @RequestBody RequestAddressDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
