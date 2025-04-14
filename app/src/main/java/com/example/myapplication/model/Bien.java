package com.example.myapplication.model;

import java.util.List;

public class Bien {
    private String id;
    private String titre;
    private String description;
    private double prix;
    private double surface;
    private int pieces;
    private String adresse;
    private List<String> images;
    private String statut;

    public Bien(double surface, String id, String titre, String description, double prix, int pieces, String adresse, List<String> images, String statut) {
        this.surface = surface;
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.pieces = pieces;
        this.adresse = adresse;
        this.images = images;
        this.statut = statut;
    }

    // Constructeur vide
    public Bien(String titre, String description, float prix, String adresse, String statut) {}

    // Constructeur complet
    public Bien(String id, String titre, String description, double prix, double surface, int pieces, String adresse, List<String> images, String statut) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.surface = surface;
        this.pieces = pieces;
        this.adresse = adresse;
        this.images = images;
        this.statut = statut;
    }



    // Getters et Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Bien{" +
                "id='" + id + '\'' +
                ", titre='" + titre + '\'' +
                ", prix=" + prix +
                ", surface=" + surface +
                ", pièces=" + pieces +
                ", statut='" + statut + '\'' +
                '}';
    }
}
