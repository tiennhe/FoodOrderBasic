package com.example.foodorderbasic.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderbasic.Model.UserModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.UserDataBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangkyActivity extends AppCompatActivity {
    EditText edtemail , edtpass , edtconfirmpass;

    Button btndangky ;
    TextView txtlogin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        edtemail = findViewById(R.id.edEmailRegister);
        edtpass = findViewById(R.id.edPassWord);
        edtconfirmpass = findViewById(R.id.edConfirmPass);


        btndangky = findViewById(R.id.register) ;
        txtlogin = findViewById(R.id.txtsignin) ;


        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangkyActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangkyAcount();
            }
        });
    }

    private void dangkyAcount() {

        String email = edtemail.getText().toString().trim();
        String password = edtpass.getText().toString().trim() ;
        String passwordconfirm = edtconfirmpass.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(this, "không được để trống email", Toast.LENGTH_SHORT).show();
        }if(password.isEmpty()){
            Toast.makeText(this, "không được để trống pass", Toast.LENGTH_SHORT).show();
        }if(passwordconfirm.isEmpty()){
            Toast.makeText(this, "vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }


        if(password.equals(passwordconfirm)){
            FirebaseAuth mAuth  = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                if(user!=null){
                                    Toast.makeText(DangkyActivity.this, user.getUid()+"", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DangkyActivity.this, "không tìm thấy user", Toast.LENGTH_SHORT).show();
                                }
                            UserModel userModel = new UserModel(user.getUid() , "","","","" ,user.getEmail());UserDataBase.getInstance(DangkyActivity.this).userDAO().insertUser(userModel);
                             Intent intent = new Intent(DangkyActivity.this , MainActivity.class);
                             startActivity(intent);
                             finishAffinity();
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(DangkyActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Lỗi");
            builder.setMessage("Mật khẩu không trùng khớp");

// Xác định nút "Đóng" trong hộp thoại
            builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Thực hiện các hành động khi người dùng nhấn nút "Đóng"
                    dialog.dismiss(); // Đóng hộp thoại
                }
            });

// Hiển thị hộp thoại
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}