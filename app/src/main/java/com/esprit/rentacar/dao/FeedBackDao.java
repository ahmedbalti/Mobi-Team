package com.esprit.rentacar.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.esprit.rentacar.entity.FeedBackModel;

import java.util.List;

@Dao
public interface FeedBackDao {

    @Insert
    void insertFeedBack(FeedBackModel feedBackModel );

    @Query("UPDATE feedback_model SET rating = :rating, contenue = :content WHERE id = :id")
    void updateRatingAndContent(int id, float rating, String content);

    @Query("SELECT * FROM feedback_model WHERE id = :id")
    FeedBackModel getFeedBackDetailsById(int id);

    @Query("SELECT * FROM feedback_model WHERE userId = :userId")
    List<FeedBackModel> getUserFeedBacks(int userId);

    @Query("SELECT * FROM feedback_model")
    List<FeedBackModel> getAllFeedBacks();

    @Query("SELECT * FROM feedback_model ORDER BY id DESC LIMIT 1")
    FeedBackModel getLatestFeedBackModels();

    @Delete
    void deleteTransaction(FeedBackModel feedBackModel);
}
