package com.dev.sav.repository;

import com.dev.sav.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNom(String nom);
}