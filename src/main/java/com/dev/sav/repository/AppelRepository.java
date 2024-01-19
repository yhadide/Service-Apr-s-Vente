package com.dev.sav.repository;

import com.dev.sav.model.Appel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppelRepository extends JpaRepository<Appel, Integer> {
    // You can add custom query methods here if needed
}
