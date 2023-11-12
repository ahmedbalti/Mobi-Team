package com.esprit.rentacar.model;

public class Reservation {
    private long id;
    private String datePrise;
    private String dateRemise;
    private String lieuPrise;
    private String lieuRemise;

    // Constructeurs
    public Reservation() {
        // Constructeur par défaut
    }

    public Reservation(String datePrise, String dateRemise, String lieuPrise, String lieuRemise) {
        this.datePrise = datePrise;
        this.dateRemise = dateRemise;
        this.lieuPrise = lieuPrise;
        this.lieuRemise = lieuRemise;
    }

    // Getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatePrise() {
        return datePrise;
    }

    public void setDatePrise(String datePrise) {
        this.datePrise = datePrise;
    }

    public String getDateRemise() {
        return dateRemise;
    }

    public void setDateRemise(String dateRemise) {
        this.dateRemise = dateRemise;
    }

    public String getLieuPrise() {
        return lieuPrise;
    }

    public void setLieuPrise(String lieuPrise) {
        this.lieuPrise = lieuPrise;
    }

    public String getLieuRemise() {
        return lieuRemise;
    }

    public void setLieuRemise(String lieuRemise) {
        this.lieuRemise = lieuRemise;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", datePrise='" + datePrise + '\'' +
                ", dateRemise='" + dateRemise + '\'' +
                ", lieuPrise='" + lieuPrise + '\'' +
                ", lieuRemise='" + lieuRemise + '\'' +
                '}';
    }
}


