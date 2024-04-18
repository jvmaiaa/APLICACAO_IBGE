package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> findAll();

    AddressResponse findById(Long id);

    AddressResponse insert(AddressRequest obj);

    AddressResponse update(Long id, AddressRequest dto);

    void delete(Long id);
}
