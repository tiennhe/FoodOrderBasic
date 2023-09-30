package com.example.foodorderbasic.Activity;

import static com.example.foodorderbasic.Activity.MainActivity.PUBLIC_REQUEST_CODE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodorderbasic.Fragnemt.CartFragment;
import com.example.foodorderbasic.Fragnemt.HomeFragment;
import com.example.foodorderbasic.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class AcountManager extends AppCompatActivity  {

    private static final int REQUEST_GALLERY_CODE = 2;
    private Toolbar toolbar ;
    private static AcountManager instance;

    private TextView txtfullname , txtgioitinh , txtngaysinh , txtsdt , txtemail ;
    private LinearLayout layoutfullname , layoutgioitinh , layoutngaysinh , layoutemail  , layoutsdt;

    private ImageView imganhdaidien ;
    private Button buttonbtnsignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_manager);

        buttonbtnsignout = findViewById(R.id.btnlogout);
        instance = this;
        innitUI();
        initLisener();

        setUserInformation();


        toolbar = findViewById(R.id.toolbarAcount);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonbtnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AcountManager.this , LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    public void innitUI(){
        txtemail = findViewById(R.id.txtupdateemail);
        txtfullname = findViewById(R.id.txtupdatefullname);
        txtgioitinh = findViewById(R.id.txtupdategioitinh);
        txtsdt = findViewById(R.id.txtupdategioitinh);
        txtngaysinh =findViewById(R.id.txtupdatetuoi);
        imganhdaidien = findViewById(R.id.imaanhdaidienupdate);

        layoutemail = findViewById(R.id.linearLayoutemail);
        layoutfullname = findViewById(R.id.linearLayoutfulllname);
        layoutgioitinh = findViewById(R.id.linearLayoutgioitinh);
        layoutngaysinh = findViewById(R.id.linearLayoutngaysinh);
        layoutsdt = findViewById(R.id.linearLayoutsdt);


    }

    private void setUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null){
            return;
        }


        txtfullname.setText(user.getDisplayName());
        txtemail.setText(user.getEmail());

        Glide.with(AcountManager.this).load(user.getPhotoUrl()).error(R.drawable.user).into(imganhdaidien);
    }

    private void initLisener(){
        imganhdaidien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openGallery();
            }
        });

    }


    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_GALLERY_CODE);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            // Xử lý ảnh đã chọn ở đây

            String [] filepath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImageUri , filepath , null , null , null);
            cursor.moveToFirst();
            int columIndex = cursor.getColumnIndex(filepath[0]);
            String picturepath = cursor.getString(columIndex);
            cursor.close();
            imganhdaidien.setImageBitmap(BitmapFactory.decodeFile(picturepath));

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(selectedImageUri)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AcountManager.this, "update sucssesfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int  i = item.getItemId() ;
        if (i== android.R.id.home) {
            // Xử lý sự kiện nút "Back" ở đây
            onBackPressed(); // Hoặc thực hiện hành động tùy chỉnh của bạn

        }
        return true;
    }



}