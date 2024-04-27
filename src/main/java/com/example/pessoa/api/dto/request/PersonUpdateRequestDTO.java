package com.example.pessoa.api.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class PersonUpdateRequestDTO {

    @Length(max = 100, message = "You have exceeded the 100 character length")
    private String name;

    @Positive(message = "The age field must be a positive number")
    private Integer age;

    @Email(message = "Invalid E-mail format")
    @Length(max = 50, message = "You have exceeded the 100 character length")
    private String email;

    private Long idAddress;
}
