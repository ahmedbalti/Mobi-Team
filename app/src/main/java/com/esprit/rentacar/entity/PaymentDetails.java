package com.esprit.rentacar.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "payment_details")
public class PaymentDetails {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String vehicleName;
    private String vehicleMatt;
    private String date;
    private String time;
    private double price;
    private String paymentMethod;
    private boolean isPaid;
    private int userId;

    // Constructeur
    public PaymentDetails(String vehicleName, String vehicleMatt, String date, String time, double price, String paymentMethod, boolean isPaid,int userId) {
        this.vehicleName = vehicleName;
        this.vehicleMatt = vehicleMatt;
        this.date = date;
        this.time = time;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.isPaid = isPaid;
        this.userId = userId ;
    }


    @Override
    public String toString() {
        return "PaymentDetails{" +
                "id=" + id +
                ", vehicleName='" + vehicleName + '\'' +
                ", vehicleMatt='" + vehicleMatt + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleMatt() {
        return vehicleMatt;
    }

    public void setVehicleMatt(String vehicleMatt) {
        this.vehicleMatt = vehicleMatt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
