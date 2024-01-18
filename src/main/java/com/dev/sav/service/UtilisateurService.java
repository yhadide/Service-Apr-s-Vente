package com.dev.sav.service;

import com.dev.sav.dto.UtilisateurDto;
import com.dev.sav.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    void saveUtilisateur(UtilisateurDto utilisateurDto);

    Utilisateur findUtilisateurByEmail(String email);

    List<UtilisateurDto> findAllUtilisateurs();
}
