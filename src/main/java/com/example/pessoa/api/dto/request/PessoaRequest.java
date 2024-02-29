package com.example.pessoa.api.dto.request;


import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "O Campo Id do Endereço é obrigatório.")
    private Long idEndereco;
}
