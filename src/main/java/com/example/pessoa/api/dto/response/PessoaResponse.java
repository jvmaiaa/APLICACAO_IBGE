package com.example.pessoa.api.dto.response;

import com.example.pessoa.api.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponse {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Address endereco;

}
