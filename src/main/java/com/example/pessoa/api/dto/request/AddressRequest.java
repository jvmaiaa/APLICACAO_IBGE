package com.example.pessoa.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "The street name field cannot be blank")
    @Length(max = 100, message = "You have exceeded the 100 character length")
    private String streetName;

    @NotNull(message = "The house number field cannot be null")
    @Positive(message = "The house number field must be a positive number")
    private Integer houseNumber;

    @NotBlank(message = "The district field cannot be blank")
    @Length(max = 100, message = "You have exceeded the 100 character length")
    private String district;

    @NotBlank(message = "The city field cannot be blank")
    @Length(max = 100, message = "You have exceeded the 100 character length")
    private String city;

    @NotBlank(message = "The state field cannot be blank")
    @Length(max = 10, message = "You have exceeded the 10 character length")
    private String state;
}
