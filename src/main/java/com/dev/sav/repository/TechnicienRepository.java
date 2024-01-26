package com.dev.sav.repository;
import com.dev.sav.model.Client;
import com.dev.sav.model.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicienRepository extends JpaRepository<Technicien, Integer> {
    Technicien findByEmail(String email);
}
