package com.example.pessoa.api.exception;

import java.io.Serial;

public class EmailCadastradoExeption extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailCadastradoExeption (String message){
        super(message);
    }
}
