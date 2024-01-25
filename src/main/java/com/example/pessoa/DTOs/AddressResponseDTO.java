package com.example.pessoa.DTOs;

import com.example.pessoa.domain.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressResponseDTO {

    private String nomeDaRua;
    private Integer numeroDaCasa;
    private String bairro;
    private String cidade;
    private String estado;

    private List<PessoaResponseDTO> pessoas;
}
