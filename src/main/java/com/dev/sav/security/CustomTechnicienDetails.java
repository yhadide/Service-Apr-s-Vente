package com.dev.sav.security;

import com.dev.sav.model.Technicien;
import com.dev.sav.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomTechnicienDetails extends org.springframework.security.core.userdetails.User {

    private final Technicien technicien;

    public CustomTechnicienDetails(Technicien technicien) {
        super(technicien.getEmail(), technicien.getMotDePasse(), mapRolesToAuthorities(technicien.getRoles()));
        this.technicien = technicien;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNom()))
                .collect(Collectors.toList());
    }
}
