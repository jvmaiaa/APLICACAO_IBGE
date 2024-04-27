package com.example.pessoa.api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddressResponseDTO {

    private Long id;
    private String streetName;
    private Integer houseNumber;
    private String district;
    private String city;
    private String state;

    private List<PersonResponseDTO> people;
}
