package com.dev.sav.security;

import com.dev.sav.model.Client;
import com.dev.sav.model.Role;
import com.dev.sav.model.Technicien;
import com.dev.sav.model.Utilisateur;
import com.dev.sav.repository.ClientRepository;
import com.dev.sav.repository.TechnicienRepository;
import com.dev.sav.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final TechnicienRepository technicienRepository;

    public CustomUtilisateurDetailsService(UtilisateurRepository utilisateurRepository, ClientRepository clientRepository, TechnicienRepository technicienRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.clientRepository = clientRepository;
        this.technicienRepository = technicienRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

        if (utilisateur != null) {
            return new org.springframework.security.core.userdetails.User(utilisateur.getEmail(),
                    utilisateur.getMotDePasse(),
                    mapRolesToAuthorities(utilisateur.getRoles()));
        }

        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            Collection<Role> clientRoles = client.getRoles();
            return new CustomUserDetails(client);
        }

        Technicien technicien = technicienRepository.findByEmail(email);
        if (technicien != null) {
            Collection<Role> technicienRoles = technicien.getRoles();
            return new CustomTechnicienDetails(technicien);
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNom()))
                .collect(Collectors.toList());
    }
}
