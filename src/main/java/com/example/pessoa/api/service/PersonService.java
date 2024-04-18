package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.PersonRequest;
import com.example.pessoa.api.dto.response.PersonResponse;

import java.util.List;

public interface PersonService {

    List<PersonResponse> findAll();

    PersonResponse findById(Long id);

    PersonResponse insert(PersonRequest obj);

    PersonResponse update(Long id, PersonRequest dto);

    void delete(Long id);
}
