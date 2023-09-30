package com.example.foodorderbasic.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.Model.UserModel;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(UserModel userModel);
    @Query("SELECT * FROM Users")
    List<UserModel> getlistUser();

    @Query("SELECT * FROM Users WHERE Uid= :id")
    List<UserModel> checkUser(String id);
    @Update
    void updateUser(UserModel userModel);



    @Delete
    void deleteUseer(UserModel userModel) ;
}
