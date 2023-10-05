package com.example.foodorderbasic.Admin;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.foodorderbasic.Fragnemt.CartFragment;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.FoodDatabase;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ShowDetailItemActivityAdmin extends AppCompatActivity {

    ImageView imgfooddetail ;
    TextView txtgiamgia ,txtnamedetail , txtgiagiamdetail , txtgiagocdetail , txtmotadetail ;
        Toolbar toolbar ;
        Button btnupdateproduct , btndeleteproduct ;
        public   String id ="" ,  name = "" , mota = "" , linkanh="";
        public double gia = 0 ,discount= 0;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_item_admin);

        toolbar = findViewById(R.id.toolbarshowdetailadmin);
        imgfooddetail = findViewById(R.id.imgshowdetailadmin);
        txtgiamgia = findViewById(R.id.txtphantramgiamgiaadmin);
        txtnamedetail = findViewById(R.id.txtnameshowdetailadmin);
        txtgiagiamdetail = findViewById(R.id.giagiamdetailadmin);
        txtgiagocdetail = findViewById(R.id.giagocdetailadmin);
        txtmotadetail = findViewById(R.id.txtmotashowdetailadmin);

        btnupdateproduct = findViewById(R.id.btnupdatecartshowdetail);
        btndeleteproduct = findViewById(R.id.btndeleteshowdetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setData();

        btnupdateproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDalogupdateProduct();
            }
        });

        btndeleteproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogDeleteproduct();
            }
        });

    }

    public void openDalogupdateProduct(){


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            // Lấy đối tượng từ Bundle
            FoodModel object = (FoodModel) bundle.getSerializable("object_key");
            id= String.valueOf(object.getId()) ;
            name = object.getName();
            linkanh = object.getImage();
            mota = object.getDescription() ;
            gia = object.getGia();
            discount = object.getDiscount();
        }
        Dialog dialog = new Dialog(ShowDetailItemActivityAdmin.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_update_product_item_admin);

        Window window = dialog.getWindow();
        if (window==null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT  , WindowManager.LayoutParams.WRAP_CONTENT);

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtidupdate , edtlinkanhupdate , edtnameupdate , edtgiaupdate , edtdiscountupdate , edtmotaupdate ;

        Button btnhuyboupdate , btnupdateproduct ;
        edtidupdate = dialog.findViewById(R.id.edtupdateid);
        edtlinkanhupdate = dialog.findViewById(R.id.edtupdateimg);
        edtnameupdate = dialog.findViewById(R.id.edtupdatename);
        edtdiscountupdate = dialog.findViewById(R.id.edtupdatediscount);
        edtmotaupdate = dialog.findViewById(R.id.edtupdatedesciption);
        edtgiaupdate = dialog.findViewById(R.id.edtupdategia);

        btnhuyboupdate = dialog.findViewById(R.id.btnadmincacleupdate);
        btnupdateproduct = dialog.findViewById(R.id.btnadminupdate);
        edtidupdate.setText(id);
        edtdiscountupdate.setText(String.valueOf(discount));
        edtmotaupdate.setText(mota);
        edtlinkanhupdate.setText(linkanh);
        edtgiaupdate.setText(String.valueOf(gia));
        edtnameupdate.setText(name);

        btnhuyboupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnupdateproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nameupdate = edtnameupdate.getText().toString().trim();

                String linkanhupdate = edtlinkanhupdate.getText().toString().trim();

                double giaupdate = Double.parseDouble(edtgiaupdate.getText().toString().trim());
                double discountupdate = Double.parseDouble(edtdiscountupdate.getText().toString().trim());
                String description = edtmotaupdate.getText().toString().trim();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                DatabaseReference databaseReference = firebaseDatabase.getReference("Product_Food");
                FoodModel foodModel = new FoodModel();
                foodModel.setName(nameupdate);
                foodModel.setGia(giaupdate);
                foodModel.setDiscount(discountupdate);
                foodModel.setDescription(description);
                foodModel.setImage(linkanhupdate);
                databaseReference.child(String.valueOf(id)).updateChildren(foodModel.toMap(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(ShowDetailItemActivityAdmin.this, "Update susessfully", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        Intent intent1 = new Intent(ShowDetailItemActivityAdmin.this , SanphamManagerActivity.class);
                        startActivity(intent1);

                    }
                });

            }
        });

        dialog.show();

    }

    public void openDialogDeleteproduct(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            FoodModel object = (FoodModel) bundle.getSerializable("object_key");

            id= String.valueOf(object.getId()) ;

        }


            new AlertDialog.Builder(ShowDetailItemActivityAdmin.this)
                    .setTitle("Delete product")
                    .setMessage("Bạn chắc chắn muốn xóa bản ghi này?")
                    .setNegativeButton("Cacel" , null)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Product_Food");

                            databaseReference.child(id).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Toast.makeText(ShowDetailItemActivityAdmin.this, "Delete sucsessfully", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(ShowDetailItemActivityAdmin.this , SanphamManagerActivity.class);
                                    startActivity(intent1);
                                }
                            });
                        }
                    }).show();

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
    public void setData(){
        // Lấy Intent và Bundle từ Intent gọi Activity đích
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            // Lấy đối tượng từ Bundle
            FoodModel object = (FoodModel) bundle.getSerializable("object_key");
            // Sử dụng đối tượng trong Activity đích
            Glide.with(this).load(object.getImage()).into(imgfooddetail);
            txtmotadetail.setText(object.getDescription());
            txtnamedetail.setText(object.getName());
            txtgiagocdetail.setText(object.getGia()+" VNĐ");
            txtgiagiamdetail.setText(object.getGia()-(object.getGia()*object.getDiscount()/100)+" VNĐ");
            txtgiamgia.setText("Giảm"+object.getDiscount()+"%");

            if(object.getDiscount()==0){
                txtgiamgia.setVisibility(View.GONE);
                txtgiagocdetail.setVisibility(View.GONE);
            }
            if(object.getDiscount()!=0){
                txtgiagocdetail.setPaintFlags(txtgiagocdetail.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

        }
    }

}