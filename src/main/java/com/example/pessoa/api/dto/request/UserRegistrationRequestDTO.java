package com.example.pessoa.api.dto.request;


import com.example.pessoa.api.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserRegistrationRequestDTO {

    private User user;
    private List<String> permissions;
}
