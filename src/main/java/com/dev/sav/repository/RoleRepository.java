package com.dev.sav.repository;

import com.dev.sav.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNom(String nom);
    default Role getOrCreateRole(String roleName) {
        Role role = findByNom(roleName);
        if (role == null) {
            role = new Role();
            role.setNom(roleName);
            role = save(role);
        }
        return role;
    }
}