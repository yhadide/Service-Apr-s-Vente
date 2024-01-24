package com.dev.sav.service;

import com.dev.sav.model.Appel;

import java.util.List;

public interface AppelService {
    List<Appel> getAllAppels();
    Appel getAppelById(int id);
    void saveAppel(Appel appel);
    void updateAppel(int id, Appel updatedAppel);
    void deleteAppel(int id);
    Appel createAppelWithDossier(Appel appel);

}
