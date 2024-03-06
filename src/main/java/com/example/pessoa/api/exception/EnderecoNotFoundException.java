package com.example.pessoa.api.exception;

import java.io.Serial;

public class EnderecoNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public EnderecoNotFoundException(String message){
        super(message);
    }
}
