package com.example.myapplication.model;

public class Agent {
    private String id;
    private String nom;
    private String email;
    private String telephone;
    private String agence;
    private String role; // ex: "admin", "agent"

    public Agent() {
        // Nécessaire pour Firebase ou Room
    }

    public Agent(String id, String nom, String email, String telephone, String agence, String role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.agence = agence;
        this.role = role;
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

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

