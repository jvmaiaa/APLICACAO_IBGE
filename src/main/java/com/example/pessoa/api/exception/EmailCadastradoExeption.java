package com.example.pessoa.api.exception;

import java.io.Serial;

public class EmailCadastradoExeption extends RuntimeException {

    private static final String EMAIL_EM_USO = "E-mail já está cadastrado no sistema";

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailCadastradoExeption (String message){
        super(message);
    }

    public EmailCadastradoExeption (){
        super(EMAIL_EM_USO);
    }

}
