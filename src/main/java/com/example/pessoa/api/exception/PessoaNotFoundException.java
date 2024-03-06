package com.example.pessoa.api.exception;

import java.io.Serial;

public class PessoaNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PessoaNotFoundException(String message) {
        super(message);
    }

}
