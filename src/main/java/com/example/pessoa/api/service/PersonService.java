package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.PersonRequestDTO;
import com.example.pessoa.api.dto.request.PersonUpdateRequestDTO;
import com.example.pessoa.api.dto.response.PersonResponseDTO;

import java.util.List;

public interface PersonService {

    List<PersonResponseDTO> findAll();

    PersonResponseDTO findById(Long id);

    PersonResponseDTO insert(PersonRequestDTO obj);

    PersonResponseDTO update(Long id, PersonUpdateRequestDTO dto);

    PersonResponseDTO updatePersonAddress(Long personId, Long AddressId);

    void delete(Long id);
}
