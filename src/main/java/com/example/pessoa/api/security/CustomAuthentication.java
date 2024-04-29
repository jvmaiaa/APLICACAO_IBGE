package com.example.pessoa.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomAuthentication implements Authentication {

    private final UserIdentification userIdentification;

    public CustomAuthentication(UserIdentification userIdentification) {
        if (userIdentification == null){
            throw new ExceptionInInitializerError("It is not possible to create a CustomAuthentication without user identification.");
        }
        this.userIdentification = userIdentification;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userIdentification
                .getPermissoes()
                .stream()
                .map(perm -> new SimpleGrantedAuthority(perm))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalStateException("Don't need to call, we are already authenticated");
    }

    @Override
    public String getName() {
        return this.userIdentification.getNome();
    }
}
