package com.dev.sav.service;

import java.util.List;

import com.dev.sav.dto.UtilisateurDto;
import com.dev.sav.model.Utilisateur;

public interface UtilisateurService {
    void saveUtilisateur(UtilisateurDto utilisateurDto);

    Utilisateur findUtilisateurByEmail(String email);

    List<UtilisateurDto> findAllUtilisateurs();
}
