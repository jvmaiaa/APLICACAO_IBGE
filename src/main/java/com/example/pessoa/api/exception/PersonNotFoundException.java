package com.example.pessoa.api.exception;

import java.io.Serial;

public class PersonNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(Long id){
        super(String.format("Pessoa com id %d não encontrada", id));
    }

}
