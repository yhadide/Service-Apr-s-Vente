package com.dev.sav.service.implementation;

import com.dev.sav.dto.UtilisateurDto;
import com.dev.sav.model.Role;
import com.dev.sav.model.Utilisateur;
import com.dev.sav.repository.RoleRepository;
import com.dev.sav.repository.UtilisateurRepository;
import com.dev.sav.service.UtilisateurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  RoleRepository roleRepository,
                                  PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDto.getMotDePasse()));

        Role role = roleRepository.findByNom("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        utilisateur.setRoles(Arrays.asList(role));
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public List<UtilisateurDto> findAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs.stream()
                .map(this::mapToUtilisateurDto)
                .collect(Collectors.toList());
    }

    private UtilisateurDto mapToUtilisateurDto(Utilisateur utilisateur) {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        String[] str = utilisateur.getNom().split(" ");

        if (str.length >= 2) {
            utilisateurDto.setPrenom(str[0]);
            utilisateurDto.setNom(str[1]);
        } else if (str.length == 1) {
            utilisateurDto.setNom(str[0]);
        }

        utilisateurDto.setEmail(utilisateur.getEmail());
        return utilisateurDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setNom("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
