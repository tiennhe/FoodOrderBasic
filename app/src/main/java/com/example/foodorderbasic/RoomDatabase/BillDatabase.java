package com.example.foodorderbasic.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodorderbasic.Model.BillModel;

@Database(entities = {BillModel.class} , version = 1)
public abstract class BillDatabase extends RoomDatabase {
    private static  final String DATABASE_BILL_NAME = "billdetailss.db";
    private static BillDatabase instance;
    public  static synchronized  BillDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext() ,BillDatabase.class ,DATABASE_BILL_NAME  )
                    .allowMainThreadQueries().build();
        }
        return  instance ;
    }
    public abstract  BillDAO billDAO();


}
