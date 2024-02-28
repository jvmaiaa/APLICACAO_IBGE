package com.example.pessoa.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequest {

    private String name;
    private Integer age;
    private String email;
    private Long idEndereco;
}
