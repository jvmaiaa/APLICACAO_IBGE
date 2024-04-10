package com.example.pessoa.api.exception;

import java.io.Serial;

public class AddressNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public AddressNotFoundException(String message){
        super(message);
    }

    public AddressNotFoundException(Long id){
        super(String.format("Endereço com id %d não encontrada", id));
    }
}
