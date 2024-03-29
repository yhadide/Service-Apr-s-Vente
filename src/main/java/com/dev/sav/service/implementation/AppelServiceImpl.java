package com.dev.sav.service.implementation;

import com.dev.sav.model.Appel;
import com.dev.sav.model.Dossier;
import com.dev.sav.repository.AppelRepository;
import com.dev.sav.repository.DossierRepository;
import com.dev.sav.service.AppelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppelServiceImpl implements AppelService {

    private final AppelRepository appelRepository;
    private final DossierRepository dossierRepository;

    @Autowired
    public AppelServiceImpl(AppelRepository appelRepository, DossierRepository dossierRepository) {
        this.appelRepository = appelRepository;
        this.dossierRepository = dossierRepository;
    }

    @Override
    public List<Appel> getAllAppels() {
        return appelRepository.findAll();
    }

    @Override
    public Appel getAppelById(int id) {
        return appelRepository.findById(id).orElse(null);
    }

    @Override
    public void saveAppel(Appel appel) {
        appelRepository.save(appel);
    }

    @Override
    public void updateAppel(int id, Appel updatedAppel) {
        Appel existingAppel = appelRepository.findById(id).orElse(null);
        if (existingAppel != null) {

            existingAppel.setDateAppel(updatedAppel.getDateAppel());
            existingAppel.setStatut(updatedAppel.getStatut());
            existingAppel.setDescription(updatedAppel.getDescription());
            existingAppel.setClient(updatedAppel.getClient());
            existingAppel.setArticle(updatedAppel.getArticle());
            existingAppel.setAppelId(updatedAppel.getAppelId());

            appelRepository.save(existingAppel);
        }
    }


    @Override
    public void deleteAppel(int id) {
        appelRepository.deleteById(id);
    }

    @Override
    public Appel createAppelWithDossier(Appel appel) {
        Appel savedAppel = appelRepository.save(appel);

        Dossier dossier = new Dossier();
        dossier.setAppel(savedAppel);
        dossier.setDateOuverture(savedAppel.getDateAppel());
        dossier.setStatut(savedAppel.getStatut());
        dossier.setDescription(savedAppel.getDescription());
        dossierRepository.save(dossier);

        return savedAppel;
    }

}
