package com.example.pessoa.api.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserIdentification {

    private String id;
    private String nome;
    private String login;
    private List<String> permissions;

    public UserIdentification(String id, String nome, String login, List<String> permissions) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.permissions = permissions;
    }

    public List<String> getPermissoes(){
        if (permissions == null){
            permissions = new ArrayList<>();
        }
        return permissions;
    }
}
