package com.example.foodorderbasic.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodorderbasic.Model.FoodModel;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void insertCart(FoodModel food);
    @Query("SELECT * FROM food_order_basics")
    List<FoodModel> getlistItemcart();

    @Query("SELECT * FROM food_order_basics WHERE name= :namecheck")
    List<FoodModel> checkitemproduct(String namecheck);
    @Update

    void updateFood(FoodModel foodModel);
    @Query("SELECT * FROM food_order_basics WHERE idbill = :userId")
    List<FoodModel> getUsersById(int userId);


    @Delete
    void deletaFood(FoodModel foodModel) ;
}
