package com.dev.sav.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "APPEL")
public class Appel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appel_id")
    private int appelId;

    @Column(name = "date_appel")
    private Date dateAppel;

    @Column(name = "statut")
    private String statut;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Appel(int appelId, Date dateAppel, String statut, String description, Client client, Article article) {
        this.appelId = appelId;
        this.dateAppel = dateAppel;
        this.statut = statut;
        this.description = description;
        this.client = client;
        this.article = article;
    }

    public Appel() {}

    public int getAppelId() {
        return appelId;
    }

    public void setAppelId(int appelId) {
        this.appelId = appelId;
    }

    public Date getDateAppel() {
        return dateAppel;
    }

    public void setDateAppel(Date dateAppel) {
        this.dateAppel = dateAppel;
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
