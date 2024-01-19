package com.dev.sav.service.implementation;

import com.dev.sav.model.Technicien;
import com.dev.sav.repository.TechnicienRepository;
import com.dev.sav.service.TechnicienService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicienServiceImpl implements TechnicienService {
    @Autowired
    private TechnicienRepository technicienRepository;

    @Override
    public List<Technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }

    @Override
    public Technicien getTechnicienById(int id) {
        return technicienRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTechnicien(Technicien technicien) {
        technicienRepository.save(technicien);
    }

    @Override
    public void updateTechnicien(int id, Technicien updatedTechnicien) {
        Technicien existingTechnicien = technicienRepository.findById(id).orElse(null);
        if (existingTechnicien != null) {
            existingTechnicien.setNom(updatedTechnicien.getNom());
            existingTechnicien.setPrenom(updatedTechnicien.getPrenom());
            existingTechnicien.setSpecialite(updatedTechnicien.getSpecialite());

            technicienRepository.save(existingTechnicien);
        }
    }

    @Override
    public void deleteTechnicien(int id) {
        technicienRepository.deleteById(id);
    }
}
