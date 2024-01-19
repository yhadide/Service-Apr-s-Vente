package com.dev.sav.model;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "MOTIF_SUIVI")
public class MotifSuivi {
    @Id
    @Column(name = "code_motif")
    private String codeMotif;

    @Column(name = "libellé")
    private String libelle;

    @Column(name = "dossier_nom")
    private String dossierNom;

    @Column(name = "date_suivi")
    private Date dateSuivi;

    @ManyToOne
    @JoinColumn(name = "dossier_nom", insertable = false, updatable = false)
    private Dossier dossier;

    public MotifSuivi(String codeMotif, String libelle, String dossierNom, Date dateSuivi, Dossier dossier) {
        this.codeMotif = codeMotif;
        this.libelle = libelle;
        this.dossierNom = dossierNom;
        this.dateSuivi = dateSuivi;
        this.dossier = dossier;
    }

    public MotifSuivi() {}

    public String getCodeMotif() {
        return codeMotif;
    }

    public void setCodeMotif(String codeMotif) {
        this.codeMotif = codeMotif;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDossierNom() {
        return dossierNom;
    }

    public void setDossierNom(String dossierNom) {
        this.dossierNom = dossierNom;
    }

    public Date getDateSuivi() {
        return dateSuivi;
    }

    public void setDateSuivi(Date dateSuivi) {
        this.dateSuivi = dateSuivi;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
}
