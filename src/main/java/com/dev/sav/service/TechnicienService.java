package com.dev.sav.service;

import com.dev.sav.dto.TechnicienDto;
import com.dev.sav.model.Technicien;

import java.util.List;

public interface TechnicienService {
    List<Technicien> getAllTechniciens();
    Technicien getTechnicienById(int id);
    Technicien findByEmail(String email);
    void saveTechnicien(TechnicienDto technicienDto);
    void updateTechnicien(int technicienId, TechnicienDto technicienDto);
    void deleteTechnicien(int id);
    long getTechnicienCount();
    void toggleTechnicienStatus(int technicienId);

}
