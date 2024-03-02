package com.example.pessoa.api.dto.request;


import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "O campo idEndereco é obrigatório.")
    private Long idEndereco;
}
