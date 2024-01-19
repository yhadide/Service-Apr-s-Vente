package com.dev.sav.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ARTICLE")
public class Article {

    @Id
    @Column(name = "reference")
    private String reference;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "prix_unitaire")
    private double prixUnitaire;


    public Article() {}

    public Article(String reference, String nom, String description, double prixUnitaire) {
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

}
