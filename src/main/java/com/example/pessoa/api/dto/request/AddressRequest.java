package com.example.pessoa.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String nomeDaRua;
    private Integer numeroDaCasa;
    private String bairro;
    private String cidade;
    private String estado;
    private Long idPessoa;
}
