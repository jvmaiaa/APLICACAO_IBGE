package com.example.pessoa.api.controller;

import com.example.pessoa.api.dto.request.AddressRequestDTO;
import com.example.pessoa.api.dto.response.AddressResponseDTO;
import com.example.pessoa.api.service.impl.AddressServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/address")
public class AddressResource {

    private final AddressServiceImpl addressService;

    @GetMapping
    @ResponseStatus(OK)
    public List<AddressResponseDTO> getAllAddres(){
        return addressService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public AddressResponseDTO findById(@PathVariable Long id){
        // Usamos o tipo OPTIONAL quando temos algum objeto que pode NÃO VIR
        return addressService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public AddressResponseDTO insertAddress(@RequestBody @Valid AddressRequestDTO obj) {
        return addressService.insert(obj);
    }


    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public AddressResponseDTO updateAddress(@PathVariable Long id,
                                            @RequestBody AddressRequestDTO dto) {
       return addressService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
    }
}
