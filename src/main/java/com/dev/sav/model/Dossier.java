package com.dev.sav.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DOSSIER")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dossier_id")
    private int dossierId;

    @ManyToOne
    @JoinColumn(name = "appel_id")
    private Appel appel;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private Technicien technicien;

    @Column(name = "date_ouverture")
    private Date dateOuverture;

    @Column(name = "date_cloture")
    private Date dateCloture;

    @Column(name = "statut")
    private String statut;

    @Column(name = "description")
    private String description;

    public Dossier(int dossierId, Appel appel, Technicien technicien, Date dateOuverture, Date dateCloture, String statut, String description) {
        this.dossierId = dossierId;
        this.appel = appel;
        this.technicien = technicien;
        this.dateOuverture = dateOuverture;
        this.dateCloture = dateCloture;
        this.statut = statut;
        this.description = description;
    }

    public Dossier() {}

    public int getDossierId() {
        return dossierId;
    }

    public void setDossierId(int dossierId) {
        this.dossierId = dossierId;
    }

    public Appel getAppel() {
        return appel;
    }

    public void setAppel(Appel appel) {
        this.appel = appel;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
