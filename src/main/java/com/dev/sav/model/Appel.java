package com.dev.sav.model;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "APPEL")
public class Appel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nº_chrono")
    private int numeroChrono;

    @Column(name = "date")
    private Date date;

    @Column(name = "heure")
    private Time heure;

    @Column(name = "motif")
    private String motif;

    @Column(name = "suite_donnée")
    private String suiteDonnee;

    @Column(name = "statut")
    private String statut;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_no_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "dossier_nom")
    private Dossier dossier;

    public Appel(int numeroChrono, Date date, Time heure, String motif, String suiteDonnee, String statut, String description, Client client, Dossier dossier) {
        this.numeroChrono = numeroChrono;
        this.date = date;
        this.heure = heure;
        this.motif = motif;
        this.suiteDonnee = suiteDonnee;
        this.statut = statut;
        this.description = description;
        this.client = client;
        this.dossier = dossier;
    }

    public Appel() {}

    public int getNumeroChrono() {
        return numeroChrono;
    }

    public void setNumeroChrono(int numeroChrono) {
        this.numeroChrono = numeroChrono;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getSuiteDonnee() {
        return suiteDonnee;
    }

    public void setSuiteDonnee(String suiteDonnee) {
        this.suiteDonnee = suiteDonnee;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
}
