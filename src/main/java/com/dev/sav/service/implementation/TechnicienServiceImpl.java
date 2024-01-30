package com.dev.sav.service.implementation;

import com.dev.sav.dto.TechnicienDto;
import com.dev.sav.model.*;
import com.dev.sav.model.Technicien;
import com.dev.sav.repository.DossierRepository;
import com.dev.sav.repository.RoleRepository;
import com.dev.sav.repository.TechnicienRepository;
import com.dev.sav.service.TechnicienService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class TechnicienServiceImpl implements TechnicienService {

    private final TechnicienRepository technicienRepository;
    private final DossierRepository dossierRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    public TechnicienServiceImpl(PasswordEncoder passwordEncoder, TechnicienRepository technicienRepository, DossierRepository dossierRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.technicienRepository = technicienRepository;
        this.dossierRepository = dossierRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }

    @Override
    public Technicien getTechnicienById(int id) {
        return technicienRepository.findById(id).orElse(null);
    }

    @Override
    public Technicien findByEmail(String email) {
        return technicienRepository.findByEmail(email);
    }

    @Override
    public void saveTechnicien(TechnicienDto technicienDto) {
        Technicien technicien = new Technicien();
        technicien.setNom(technicienDto.getNom());
        technicien.setPrenom(technicienDto.getPrenom());
        technicien.setEmail(technicienDto.getEmail());
        technicien.setSpecialite(technicienDto.getSpecialite());
        technicien.setMotDePasse(passwordEncoder.encode(technicienDto.getMotDePasse()));

        Role role = roleRepository.getOrCreateRole("ROLE_TECHNICIEN");
        technicien.setRoles(Arrays.asList(role));

        technicienRepository.save(technicien);
    }

    @Override
    public void updateTechnicien(int technicienId, TechnicienDto technicienDto) {

        Technicien technicien = findByEmail(technicienDto.getEmail());
        if (technicienDto.getMotDePasse() != null && !technicienDto.getMotDePasse().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(technicienDto.getMotDePasse());
            technicien.setMotDePasse(encodedPassword);
        }
        technicien.setNom(technicienDto.getNom());
        technicien.setPrenom(technicienDto.getPrenom());
        technicien.setEmail(technicienDto.getEmail());
        technicien.setSpecialite(technicienDto.getSpecialite());
        technicienRepository.save(technicien);
    }

    @Override
    public void deleteTechnicien(int id) {
        technicienRepository.deleteById(id);
    }
    public long getTechnicienCount() {
        return technicienRepository.count();
    }

    @Override
    public void toggleTechnicienStatus(int technicienId) {
        Technicien technicien = technicienRepository.findById(technicienId).orElse(null);
        boolean currentStatus = technicien.isActive();
        technicien.setActive(!currentStatus);
        technicienRepository.save(technicien);
    }

}
