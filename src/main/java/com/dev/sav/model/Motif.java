package com.dev.sav.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MOTIF")
public class Motif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motif_id")
    private int motifId;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private Dossier dossier;

    @Column(name = "nom")
    private String nom;

    public Motif(int motifId, Dossier dossier, String nom) {
        this.motifId = motifId;
        this.dossier = dossier;
        this.nom = nom;
    }

    public Motif() {}

    public int getMotifId() {
        return motifId;
    }

    public void setMotifId(int motifId) {
        this.motifId = motifId;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
