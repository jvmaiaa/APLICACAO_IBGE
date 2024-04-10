package com.example.pessoa.api.exception;

import java.io.Serial;

public class EmailRegisteredExeption extends RuntimeException {

    private static final String EMAIL_EM_USO = "E-mail já está cadastrado no sistema";

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailRegisteredExeption(String message){
        super(message);
    }

    public EmailRegisteredExeption(){
        super(EMAIL_EM_USO);
    }

}
