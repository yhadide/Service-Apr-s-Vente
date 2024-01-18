package com.dev.sav.model;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "ARTICLE")
public class Article {
    @Id
    @Column(name = "référence")
    private String reference;

    @Column(name = "libellé")
    private String libelle;

    @Column(name = "prix")
    private double prix;

    @Column(name = "stock")
    private int stock;

    public Article() {}

    public Article(String reference, String libelle, double prix, int stock) {
        this.reference = reference;
        this.libelle = libelle;
        this.prix = prix;
        this.stock = stock;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
