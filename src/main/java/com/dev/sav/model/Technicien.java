package com.dev.sav.model;
import jakarta.persistence.*;
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

    @Column(name = "specialite")
    private String specialite;

    @OneToMany(mappedBy = "technicien", cascade = CascadeType.ALL)
    private List<Dossier> dossiers;

    public Technicien(int idTechnicien, String nom, String specialite, List<Dossier> dossiers) {
        this.idTechnicien = idTechnicien;
        this.nom = nom;
        this.specialite = specialite;
        this.dossiers = dossiers;
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

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(List<Dossier> dossiers) {
        this.dossiers = dossiers;
    }
}