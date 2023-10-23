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

    @Query("SELECT * FROM bill_list_produts")
    List<BillModel> getlistBill();

    @Query("SELECT * FROM bill_list_produts Where Uid = :id")
    List<BillModel> getlistBillid(String id);
    @Query("SELECT * FROM bill_list_produts Where Uid = :id and status_Bill= :status")
    List<BillModel> getlistBillidbyStatus(String id , int status);
    @Query("SELECT * FROM bill_list_produts Where status_Bill = :status")
    List<BillModel> getlistBillbyStatus(int status);



    @Query("SELECT * FROM bill_list_produts WHERE  date >=  :startDate  and date<= :enddate")
    List<BillModel> getProductsByDateRange(String startDate , String enddate);

    @Query("SELECT * FROM bill_list_produts ORDER BY idbill DESC LIMIT 1")
    BillModel getLastBill();
    @Update
    void updateBill(BillModel billModel);

    @Delete
    void deleteBill(BillModel billModel) ;
}
