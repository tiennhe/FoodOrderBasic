package com.example.foodorderbasic.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodorderbasic.AdapterAdmin.UserManagerAdapter;
import com.example.foodorderbasic.Model.UserModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.UserDataBase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class AcountUserManagerActivity extends AppCompatActivity {
        ArrayList<UserModel> list = new ArrayList<>();
        UserManagerAdapter adapter ;
        RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_user_manager);

        recyclerView = findViewById(R.id.rcluserManagerAdmin);
        LinearLayoutManager manager = new LinearLayoutManager(AcountUserManagerActivity.this);
        list = (ArrayList<UserModel>) UserDataBase.getInstance(AcountUserManagerActivity.this).userDAO().getlistUser();
        adapter = new UserManagerAdapter(list , AcountUserManagerActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();





    }

}