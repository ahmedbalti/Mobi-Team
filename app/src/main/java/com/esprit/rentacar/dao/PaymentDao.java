package com.esprit.rentacar.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.esprit.rentacar.entity.PaymentDetails;

import java.util.List;

@Dao
public interface PaymentDao {

    @Insert
    void insertPaymentDetails(PaymentDetails paymentDetails);

    @Update
    void updatePaymentDetails(PaymentDetails paymentDetails);

    @Query("SELECT * FROM payment_details WHERE id = :id")
    PaymentDetails getPaymentDetailsById(int id);

    @Query("SELECT * FROM payment_details WHERE isPaid = 1")
    List<PaymentDetails> getPaidPayments();

    @Query("SELECT * FROM payment_details WHERE userId = :userId")
    List<PaymentDetails> getUserTransactions(int userId);

    @Query("SELECT * FROM payment_details ORDER BY id DESC LIMIT 1")
    PaymentDetails getLatestPaymentDetails();

    @Delete
    void deleteTransaction(PaymentDetails transaction);






}
