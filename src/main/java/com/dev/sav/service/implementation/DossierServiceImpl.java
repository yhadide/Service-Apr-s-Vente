package com.dev.sav.service.implementation;

import com.dev.sav.model.Appel;
import com.dev.sav.model.Dossier;
import com.dev.sav.repository.DossierRepository;
import com.dev.sav.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierServiceImpl implements DossierService {

    private final DossierRepository dossierRepository;

    @Autowired
    public DossierServiceImpl(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }

    @Override
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    @Override
    public Dossier getDossierById(int id) {
        return dossierRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDossier(Dossier dossier) {
        dossierRepository.save(dossier);
    }

    @Override
    public void updateDossier(int id, Dossier updatedDossier) {
        Dossier existingDossier = dossierRepository.findById(id).orElse(null);
        if (existingDossier != null) {

            existingDossier.setDateCloture(updatedDossier.getDateCloture());
            existingDossier.setStatut(updatedDossier.getStatut());
            existingDossier.setTechnicien(updatedDossier.getTechnicien());
            Appel appel = existingDossier.getAppel();
            appel.setStatut(updatedDossier.getStatut());

            dossierRepository.save(existingDossier);
        }
    }

    @Override
    public void deleteDossier(int id) {
        dossierRepository.deleteById(id);
    }
}
