package com.example.pessoa.api.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String handlePessoaNotFoundException(PersonNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleEnderecoNotFoundException(AddressNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmailRegisteredExeption.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleEmailCadastradoExeption(EmailRegisteredExeption e){
        return e.getMessage();
    }
}
