package com.dev.sav.security;

import com.dev.sav.model.Client;
import com.dev.sav.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private final Client client;

    public CustomUserDetails(Client client) {
        super(client.getEmail(), client.getMotDePasse(), mapRolesToAuthorities(client.getRoles()));
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNom()))
                .collect(Collectors.toList());
    }
}
