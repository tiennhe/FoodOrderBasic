package com.example.foodorderbasic.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodorderbasic.Activity.AcountManager;
import com.example.foodorderbasic.Activity.LoginActivity;
import com.example.foodorderbasic.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
        ConstraintLayout layoutdoanhthu , layoutquanlisanpham , layoutquanlidonhang , layoutquanlyuser;

        ImageView imglogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        innitUi();

        initListener();
    }


    public void innitUi(){
        layoutdoanhthu = findViewById(R.id.layoutdoanhthu);
        layoutquanlidonhang = findViewById(R.id.layoutquanlidonhang);
        layoutquanlisanpham = findViewById(R.id.layoutquanlisanpham);
        layoutquanlyuser = findViewById(R.id.layoutquanliaccount);
        imglogout = findViewById(R.id.imgLogout);
    }

    public void initListener(){
        layoutdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminActivity.this , DoanhthuActivity.class);
                startActivity(intent);
            }
        });

        layoutquanlisanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminActivity.this, "quản lí sản phẩm", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AdminActivity.this , SanphamManagerActivity.class);
                startActivity(intent);
            }
        });
        layoutquanlidonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminActivity.this , DonhangfManagerActivity.class);
                startActivity(intent);
            }
        });
        layoutquanlyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminActivity.this , AcountUserManagerActivity.class);
                startActivity(intent);
            }
        });

        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AdminActivity.this , LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}