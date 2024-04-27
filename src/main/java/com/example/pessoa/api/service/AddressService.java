package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.AddressRequestDTO;
import com.example.pessoa.api.dto.response.AddressResponseDTO;

import java.util.List;

public interface AddressService {

    List<AddressResponseDTO> findAll();

    AddressResponseDTO findById(Long id);

    AddressResponseDTO insert(AddressRequestDTO obj);

    AddressResponseDTO update(Long id, AddressRequestDTO dto);

    void delete(Long id);
}
