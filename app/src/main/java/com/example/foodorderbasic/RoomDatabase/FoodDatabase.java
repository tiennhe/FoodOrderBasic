package com.example.foodorderbasic.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodorderbasic.Model.FoodModel;

@Database(entities = {FoodModel.class} , version = 1)
public abstract class FoodDatabase extends RoomDatabase {

    private static  final String DATABASE_NAME = "foodordersbasic.db";
    private static FoodDatabase instance;
    public  static synchronized  FoodDatabase getInstance(Context context){
    if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext() ,FoodDatabase.class ,DATABASE_NAME  )
                    .allowMainThreadQueries().build();
    }
    return  instance ;
    }
    public abstract  FoodDao foodDao();
}