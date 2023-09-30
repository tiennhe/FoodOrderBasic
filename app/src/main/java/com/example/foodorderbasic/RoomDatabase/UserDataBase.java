package com.example.foodorderbasic.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.Model.UserModel;

@Database(entities = {UserModel.class} , version = 1)
public abstract class UserDataBase extends RoomDatabase {
    private static  final String DATABASE_NAME = "users.db";
    private static UserDataBase instance;
    public  static synchronized  UserDataBase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext() ,UserDataBase.class ,DATABASE_NAME  )
                    .allowMainThreadQueries().build();
        }
        return  instance ;
    }
    public abstract  UserDAO userDAO();

}
