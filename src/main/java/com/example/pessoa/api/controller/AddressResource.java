package com.example.pessoa.api.controller;

import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.dto.response.AddressResponse;
import com.example.pessoa.api.service.impl.AddressServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v2")
public class AddressResource {

    private final AddressServiceImpl addressService;

    @GetMapping
    @ResponseStatus(OK)
    public List<AddressResponse> getAllAddres(){
        return addressService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public AddressResponse findById(@PathVariable Long id){
        // Usamos o tipo OPTIONAL quando temos algum objeto que pode NÃO VIR
        return addressService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public AddressResponse insertAddress(@RequestBody @Valid AddressRequest obj) {
        return addressService.insert(obj);
    }


    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public AddressResponse updateAddress(@PathVariable Long id,
                                         @RequestBody AddressRequest dto) {
       return addressService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
    }
}
