package com.example.foodorderbasic.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.Model.FoodModel;

import java.util.List;

@Dao
public interface BillDAO {
    @Insert
    void insertBill(BillModel model);

    @Query("SELECT * FROM bill_list_products")
    List<BillModel> getlistBill();

    @Update
    void updateBill(BillModel billModel);

    @Delete
    void deleteBill(BillModel billModel) ;
}
