package com.dev.sav.model;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "DOSSIER")
public class Dossier {
    @Id
    @Column(name = "nom")
    private String nom;

    @Column(name = "relève_de")
    private int releveDe;

    @Column(name = "technicien_idt_technicien")
    private int technicienIdTechnicien;

    @Column(name = "appel_nº_chrono")
    private int appelNumeroChrono;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "relève_de", insertable = false, updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "technicien_idt_technicien", insertable = false, updatable = false)
    private Technicien technicien;

    @OneToOne(mappedBy = "dossier", cascade = CascadeType.ALL)
    private Appel appel;

    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL)
    private List<MotifSuivi> motifsSuivi;

    public Dossier(String nom, int releveDe, int technicienIdTechnicien, int appelNumeroChrono, Date dateCreation, String description, Client client, Technicien technicien, Appel appel, List<MotifSuivi> motifsSuivi) {
        this.nom = nom;
        this.releveDe = releveDe;
        this.technicienIdTechnicien = technicienIdTechnicien;
        this.appelNumeroChrono = appelNumeroChrono;
        this.dateCreation = dateCreation;
        this.description = description;
        this.client = client;
        this.technicien = technicien;
        this.appel = appel;
        this.motifsSuivi = motifsSuivi;
    }

    public Dossier() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getReleveDe() {
        return releveDe;
    }

    public void setReleveDe(int releveDe) {
        this.releveDe = releveDe;
    }

    public int getTechnicienIdTechnicien() {
        return technicienIdTechnicien;
    }

    public void setTechnicienIdTechnicien(int technicienIdTechnicien) {
        this.technicienIdTechnicien = technicienIdTechnicien;
    }

    public int getAppelNumeroChrono() {
        return appelNumeroChrono;
    }

    public void setAppelNumeroChrono(int appelNumeroChrono) {
        this.appelNumeroChrono = appelNumeroChrono;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    public Appel getAppel() {
        return appel;
    }

    public void setAppel(Appel appel) {
        this.appel = appel;
    }

    public List<MotifSuivi> getMotifsSuivi() {
        return motifsSuivi;
    }

    public void setMotifsSuivi(List<MotifSuivi> motifsSuivi) {
        this.motifsSuivi = motifsSuivi;
    }
}
