package com.example.pessoa.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;
    private String nomeDaRua;
    private Integer numeroDaCasa;
    private String bairro;
    private String cidade;
    private String estado;

    private List<PessoaResponse> pessoas;
}
