package com.example.myapplication.model;
public class Client {
    private String id;
    private String nom;
    private String email;
    private String telephone;
    private String statut; // ex: "intéressé", "en visite", "acheteur"
    private String note;   // note interne ou historique d’échange

    public Client() {
        // Nécessaire pour Firebase ou Room
    }

    public Client(String id, String nom, String email, String telephone, String statut, String note) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.statut = statut;
        this.note = note;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

