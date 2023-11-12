package com.esprit.rentacar.Model;

// Dans le package model ou entity

public class Voiture {

    private int id;


    private String nom;


    private String modele;

    public Voiture() {
        // Initialisez d'autres champs si nécessaire
    }

    // Ajoutez d'autres champs en fonction de vos besoins

    // Constructeur
    public Voiture(String marque, String modele) {
        this.nom = nom;
        this.modele = modele;
        // Initialisez d'autres champs si nécessaire
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }


}
