package com.example.pessoa.api.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(PessoaNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String handlePessoaNotFoundException(PessoaNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(EnderecoNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleEnderecoNotFoundException(EnderecoNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmailCadastradoExeption.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleEmailCadastradoExeption(EmailCadastradoExeption e){
        return e.getMessage();
    }
}
