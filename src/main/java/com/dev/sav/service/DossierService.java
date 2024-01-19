package com.dev.sav.service;

import com.dev.sav.model.Dossier;

import java.util.List;

public interface DossierService {
    List<Dossier> getAllDossiers();
    Dossier getDossierById(int id);
    void saveDossier(Dossier dossier);
    void updateDossier(int id, Dossier updatedDossier);
    void deleteDossier(int id);
}
