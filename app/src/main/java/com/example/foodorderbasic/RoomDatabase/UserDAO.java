package com.example.foodorderbasic.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodorderbasic.Model.BillModel;
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

    @Query("SELECT * FROM Users Where Uid = :id")
    UserModel getUserid(String id);

    @Query("UPDATE Users SET sdt = :newField1 WHERE Uid = :itemId")
    void updatesdttUserbyid(String itemId, String newField1);

    @Query("UPDATE Users SET gioitinh = :newField1 WHERE Uid = :itemId")
    void updategioitinhtUserbyid(String itemId, String newField1);
    @Query("UPDATE Users SET ngaysinh = :newField1 WHERE Uid = :itemId")
    void updatenagysinhtUserbyid(String itemId, String newField1);

    @Delete
    void deleteUseer(UserModel userModel) ;
}
