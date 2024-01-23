package com.example.pessoa.DTOs;

import com.example.pessoa.domain.Address;

public record RequestAddressDTO(String nomeDaRua, Integer numeroDaCasa, String bairro, String cidade, String estado) {
}
