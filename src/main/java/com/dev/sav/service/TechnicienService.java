package com.dev.sav.service;

import com.dev.sav.model.Technicien;

import java.util.List;

public interface TechnicienService {
    List<Technicien> getAllTechniciens();
    Technicien getTechnicienById(int id);
    void saveTechnicien(Technicien technicien);
    void updateTechnicien(int id, Technicien updatedTechnicien);
    void deleteTechnicien(int id);
    long getTechnicienCount();

}
