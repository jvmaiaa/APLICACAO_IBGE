package com.example.pessoa.DTOs;

import lombok.NonNull;

public record RequestAddressDTO(
        @NonNull String nomeDaRua,
        @NonNull Integer numeroDaCasa,
        @NonNull String bairro,
        @NonNull String cidade,
        @NonNull String estado,
        @NonNull Long idPessoa) {
}
