package com.example.pessoa.api.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
public class PersonResponse {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long idAddress;
}
