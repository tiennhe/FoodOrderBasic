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
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodorderbasic.Fragnemt.CartFragment;
import com.example.foodorderbasic.Fragnemt.HomeFragment;
import com.example.foodorderbasic.Model.UserModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.UserDataBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.Calendar;

public class AcountManager extends AppCompatActivity  {

    private static final int REQUEST_GALLERY_CODE = 2;
    private Toolbar toolbar ;

    private  String gioitinh = "";
    private  String ngaysinhdialog = "";
    private static AcountManager instance;
private   UserModel userModel = new UserModel();
    private TextView txtfullname , txtgioitinh , txtngaysinh , txtsdt , txtemail ;
    private LinearLayout layoutfullname , layoutgioitinh , layoutngaysinh , layoutemail  , layoutsdt;
    RadioGroup radioGroup ;

    private RadioButton rdoname , rdonu;
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
        txtsdt = findViewById(R.id.txtupdatephone);
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

        layoutfullname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            opentEditFullname();
            }
        });
        layoutsdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentEditPhonenumber();
            }
        });
        layoutgioitinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentEditgioitinh();
            }
        });
        layoutngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onpenPickerdatedialog();
            }
        });
        layoutemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AcountManager.this, "không được sửa trường này", Toast.LENGTH_SHORT).show();
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
                                imganhdaidien.setImageBitmap(BitmapFactory.decodeFile(picturepath));
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

    private void opentEditFullname(){
            Dialog dialog = new Dialog(AcountManager.this);
            dialog.setContentView(R.layout.layout_dialog_edit_profile);

            Button  btnhuy = dialog.findViewById(R.id.btnhuy);

            Button btnupdate = dialog.findViewById(R.id.btnupdate);
            EditText edtdata  =dialog.findViewById(R.id.edtdata);

            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fullname = edtdata.getText().toString().trim();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(fullname)
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AcountManager.this, "update sucsessfully", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        txtfullname.setText(user.getDisplayName());
                                    }
                                }
                            });
                }
            });

            btnhuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
    }
    private void opentEditPhonenumber(){
        Dialog dialog = new Dialog(AcountManager.this);
        dialog.setContentView(R.layout.layout_dialog_edit_profile);

        Button  btnhuy = dialog.findViewById(R.id.btnhuy);

        Button btnupdate = dialog.findViewById(R.id.btnupdate);
        EditText edtdata  =dialog.findViewById(R.id.edtdata);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneMumber = edtdata.getText().toString().trim();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user!=null){
                    UserDataBase.getInstance(AcountManager.this).userDAO().updatesdttUserbyid(user.getUid(),PhoneMumber);

                    Toast.makeText(AcountManager.this, "update thành công", Toast.LENGTH_SHORT).show();
                    UserModel userModel1 = new UserModel();
                    userModel1 =UserDataBase.getInstance(AcountManager.this).userDAO().getUserid(user.getUid());
                    dialog.dismiss();
                    txtsdt.setText(userModel1.getSdt());
                }else{
                    Toast.makeText(AcountManager.this, "không tìm thấy user", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    private void opentEditgioitinh(){
        Dialog dialog = new Dialog(AcountManager.this);
        dialog.setContentView(R.layout.layout_dialog_edit_gioitinh);

        rdoname = dialog.findViewById(R.id.rdoNam);
        rdonu = dialog.findViewById(R.id.rdoNu);
        radioGroup = dialog.findViewById(R.id.groupgioitinh);


        Button  btnhuy = dialog.findViewById(R.id.btnhuygioitinh);

        Button btnupdate = dialog.findViewById(R.id.btnupdategioitinh);


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


           if(rdoname.isChecked()){
               gioitinh = "Nam";
           }if(rdonu.isChecked()){
               gioitinh = "Nữ";
                }

 

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){

                    Toast.makeText(AcountManager.this, gioitinh+"", Toast.LENGTH_SHORT).show();
                    UserDataBase.getInstance(AcountManager.this).userDAO().updategioitinhtUserbyid(user.getUid() , gioitinh);
                    Toast.makeText(AcountManager.this, "update thành công", Toast.LENGTH_SHORT).show();
                    UserModel userModel1 = new UserModel();
                    userModel1 =UserDataBase.getInstance(AcountManager.this).userDAO().getUserid(user.getUid());
                    dialog.dismiss();
                    Toast.makeText(AcountManager.this, userModel1.getGioitinh()+"giới tính", Toast.LENGTH_SHORT).show();
                    txtgioitinh.setText(userModel1.getGioitinh());
                }else{
                    Toast.makeText(AcountManager.this, "không tìm thấy user", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void onpenPickerdatedialog(){


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog  datePickerDialog = new DatePickerDialog(AcountManager.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                ngaysinhdialog = String.format("%d/%d/%d" , i2 , i1+1 , i);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    UserDataBase.getInstance(AcountManager.this).userDAO().updatenagysinhtUserbyid(user.getUid() ,ngaysinhdialog );
                    Toast.makeText(AcountManager.this, "update thành công", Toast.LENGTH_SHORT).show();
                    UserModel userModel1 = new UserModel();
                    userModel1 =UserDataBase.getInstance(AcountManager.this).userDAO().getUserid(user.getUid());
                    txtngaysinh.setText(userModel1.getNgaysinh());
                }else{
                    Toast.makeText(AcountManager.this, "không tìm thấy user", Toast.LENGTH_SHORT).show();
                }

            }
        } , year , month , day);



        datePickerDialog.show();


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        Uri imgaanh = user.getPhotoUrl();
        Glide.with(AcountManager.this).load(imgaanh).into(imganhdaidien);

      UserModel userModel1 = new UserModel();
        userModel1 =UserDataBase.getInstance(AcountManager.this).userDAO().getUserid(user.getUid());

        Toast.makeText(AcountManager.this, userModel1.getSdt()+"", Toast.LENGTH_SHORT).show();
        txtsdt.setText(userModel1.getSdt());
        txtgioitinh.setText(userModel1.getGioitinh());
        txtngaysinh.setText(userModel1.getNgaysinh());
        txtemail.setText(user.getEmail());
        txtfullname.setText(user.getDisplayName());
    }
}