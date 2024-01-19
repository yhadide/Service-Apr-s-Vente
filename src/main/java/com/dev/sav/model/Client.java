package com.dev.sav.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_client")
    private int noClient;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "email")
    private String email;

    @Column(name = "motDePasse;")
    private String motDePasse;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Appel> appels;


    public Client(int noClient, String nom, String prenom, String adresse, String email, String telephone,String motDePasse, List<Appel> appels) {
        this.noClient = noClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.appels = appels;
        this.motDePasse = motDePasse;
    }

    public Client() {}

    public int getNoClient() {
        return noClient;
    }

    public void setNoClient(int noClient) {
        this.noClient = noClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Appel> getAppels() {
        return appels;
    }

    public void setAppels(List<Appel> appels) {
        this.appels = appels;
    }


    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @ManyToMany(fetch = FetchType.EAGER)  // Assuming Many-to-Many relationship with roles
    @JoinTable(
            name = "client_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}