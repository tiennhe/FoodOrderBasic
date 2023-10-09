package com.example.foodorderbasic.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderbasic.Admin.AdminActivity;
import com.example.foodorderbasic.Model.UserModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.UserDataBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    EditText edtemai , edtpass ;
    Button btndangnhap ;
    TextView txtdangky ;
    ArrayList<UserModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtemai = findViewById(R.id.til_Email);
        edtpass = findViewById(R.id.til_Pass);
        list = new ArrayList<>();

        btndangnhap = findViewById(R.id.btnSignIn);
        txtdangky = findViewById(R.id.tvdangky);

        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this   , DangkyActivity.class);
                startActivity(intent);
            }
        });

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangnhap();
            }
        });
    }

    private void dangnhap() {
        String email = edtemai.getText().toString().trim() ;
        String pass = edtpass.getText().toString().trim() ;

        FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();
        list = (ArrayList<UserModel>) UserDataBase.getInstance(LoginActivity.this).userDAO().getlistUser();
        for (int i = 0; i <list.size() ; i++) {
            if(email.equals(list.get(i).getEmail())){
                FirebaseAuth  mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    if(email.equals("admin@gmail.com")){
                                        Intent intent1 = new Intent(LoginActivity.this , AdminActivity.class);
                                        startActivity(intent1);
                                    }
                                    else {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                    finishAffinity();
                                }else {
                                    Toast.makeText(LoginActivity.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }

    }
}