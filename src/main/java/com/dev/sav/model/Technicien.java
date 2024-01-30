package com.dev.sav.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "TECHNICIEN")
public class Technicien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idt_technicien")
    private int idTechnicien;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "specialite")
    private String specialite;
    @Column(name = "email")
    private String email;

    @Column(name = "motDePasse")
    @JsonIgnore
    private String motDePasse;
    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "technicien", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Dossier> dossiers;

    public Technicien(int idTechnicien, String nom, String prenom, String specialite, String email, String motDePasse, List<Dossier> dossiers,boolean isActive) {
        this.idTechnicien = idTechnicien;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.email = email;
        this.motDePasse = motDePasse;
        this.dossiers = dossiers;
        this.isActive = isActive;
    }

    public Technicien() {}

    public int getIdTechnicien() {
        return idTechnicien;
    }

    public void setIdTechnicien(int idTechnicien) {
        this.idTechnicien = idTechnicien;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(List<Dossier> dossiers) {
        this.dossiers = dossiers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    @ManyToMany(fetch = FetchType.EAGER)  // Assuming Many-to-Many relationship with roles
    @JoinTable(
            name = "technicien_roles",
            joinColumns = @JoinColumn(name = "technicien_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}