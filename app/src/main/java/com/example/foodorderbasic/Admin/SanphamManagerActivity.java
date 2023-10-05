package com.example.foodorderbasic.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodorderbasic.AdapterAdmin.FoodproductAdapterAdmin;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SanphamManagerActivity extends AppCompatActivity {


    RecyclerView rclshowlistproduct ;
    ArrayList<FoodModel> list ;
    FoodproductAdapterAdmin adapterAdmin ;
    FloatingActionButton btnadd ;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham_manager);

        btnadd = findViewById(R.id.flbtnaddproduct);

        rclshowlistproduct = findViewById(R.id.rcvlistproductadmin);
        toolbar = findViewById(R.id.toolbarquanliproduct);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getdataFormFirebase();

        list = new ArrayList<>();

        GridLayoutManager manager = new GridLayoutManager(SanphamManagerActivity.this , 2);

        adapterAdmin = new FoodproductAdapterAdmin(SanphamManagerActivity.this  , list);
        rclshowlistproduct.setLayoutManager(manager);
        rclshowlistproduct.setAdapter(adapterAdmin);
        adapterAdmin.notifyDataSetChanged();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(SanphamManagerActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_add_product_item_admin);

                Window window = dialog.getWindow();
                if (window==null){
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT  , WindowManager.LayoutParams.WRAP_CONTENT);

                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText edtid , edtlinkanh , edtname , edtgia , edtdiscount , edtmota ;

                Button btnhuybo , btnaddproduct ;
                edtid = dialog.findViewById(R.id.edtaddid);
                edtlinkanh = dialog.findViewById(R.id.edtaddimg);
                edtname = dialog.findViewById(R.id.edtaddname);
                edtdiscount = dialog.findViewById(R.id.edtadddiscount);
                edtmota = dialog.findViewById(R.id.edtadddesciption);
                edtgia = dialog.findViewById(R.id.edtaddgia);

                btnhuybo = dialog.findViewById(R.id.btnadmincacle);
                btnaddproduct = dialog.findViewById(R.id.btnadminadd);

                btnhuybo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnaddproduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id= Integer.parseInt(edtid.getText().toString().trim());
                        String linkanh = edtlinkanh.getText().toString().trim();
                        double gia = Double.parseDouble(edtgia.getText().toString().trim());
                        double discount = Double.parseDouble(edtdiscount.getText().toString().trim());
                        String description = edtmota.getText().toString().trim();
                        String name = edtname.getText().toString().trim();

                        FoodModel foodModel = new FoodModel(id , name , gia , discount ,description , linkanh );

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                        DatabaseReference databaseReference = firebaseDatabase.getReference("Product_Food");
                        String pathObject = String.valueOf(id);
                        databaseReference.child(pathObject).setValue(foodModel, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(SanphamManagerActivity.this, "them thanh cong", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            }
                        });

                    }
                });
                dialog.show();
            }
        });


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

    @Override
    protected void onStart() {
        super.onStart();

 if(!list.isEmpty()){
     list.clear();
     getdataFormFirebase();
 }


    }

    private void getdataFormFirebase() {
        try {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;

            DatabaseReference databaseReference = firebaseDatabase.getReference("Product_Food");
            Log.d("url", "getdataFormFirebase: "+databaseReference);

            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    FoodModel model = snapshot.getValue(FoodModel.class);
                    if(model!=null){
                        list.add(model);
                        adapterAdmin.notifyDataSetChanged();
                    }
                    adapterAdmin.notifyDataSetChanged();

                }



                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    FoodModel model = snapshot.getValue(FoodModel.class);
                    if( model==null||list==null|| list.isEmpty()){
                        return;
                    }

                    for (int i = 0; i < list.size(); i++) {
                        if(model.getId() ==list.get(i).getId()){
                            list.set(i , model);
                        }

                    }

                    adapterAdmin.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    FoodModel model = snapshot.getValue(FoodModel.class);
                    if(model==null ||list==null ||list.isEmpty()){
                        return;
                    }
                    for (int i = 0; i < list.size(); i++) {
                            if(model.getId()==list.get(i).getId()){
                                list.remove(list.get(i));
                                break;
                            }
                    }
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            Toast.makeText(SanphamManagerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}