package com.example.pessoa.DTOs;

import com.example.pessoa.domain.Address;
import lombok.Getter;
import lombok.Setter;

public record RequestAddressDTO(String nomeDaRua, Integer numeroDaCasa, String bairro, String cidade, String estado, Long id) {
}
