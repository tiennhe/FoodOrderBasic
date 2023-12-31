package com.example.foodorderbasic.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodorderbasic.Adapter.ListImageProductsAdapter;
import com.example.foodorderbasic.Fragnemt.CartFragment;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.FoodDatabase;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailItemActivity extends AppCompatActivity {

    ImageView imgfooddetail;
    TextView txtgiamgia, txtnamedetail, txtgiagiamdetail, txtgiagocdetail, txtmotadetail;
    Toolbar toolbar;
    Button btnshowbottomshetdilog;
    RecyclerView recyclerViewlistimage;

    private ListImageProductsAdapter imageAdapter;
    private List<String> listImage;

    private String idproduct = "";


    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_item);

        toolbar = findViewById(R.id.toolbarshowdetail);

        imgfooddetail = findViewById(R.id.imgshowdetail);
        txtgiamgia = findViewById(R.id.txtphantramgiamgia);
        txtnamedetail = findViewById(R.id.txtnameshowdetail);
        txtgiagiamdetail = findViewById(R.id.giagiamdetail);
        txtgiagocdetail = findViewById(R.id.giagocdetail);
        txtmotadetail = findViewById(R.id.txtmotashowdetail);
        recyclerViewlistimage = findViewById(R.id.rcliewimgother);
        btnshowbottomshetdilog = findViewById(R.id.btnaddtocartshowdetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setData();

        btnshowbottomshetdilog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOpenButtomsheetDilog();
            }
        });

    }

    private void onOpenButtomsheetDilog() {

        View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottomsheet, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(false);
        Button btncancle = viewDialog.findViewById(R.id.btncancel);
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        TextView txtname, txtsoluong, txtgia;
        Button btnaddtocart, btnthem, btngiam;
        ImageView imgbuttomsheet = viewDialog.findViewById(R.id.imgbottomshet);
        btnaddtocart = viewDialog.findViewById(R.id.btnaddtocart);
        btnthem = viewDialog.findViewById(R.id.btntang);
        btngiam = viewDialog.findViewById(R.id.btngiam);
        txtname = viewDialog.findViewById(R.id.txtnamebottomsheet);
        txtsoluong = viewDialog.findViewById(R.id.tvquantity);
        txtgia = viewDialog.findViewById(R.id.txtgiabanbottomsheet);

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                // Lấy đối tượng từ Bundle
                FoodModel object = (FoodModel) bundle.getSerializable("object_key");
                String name = txtname.getText().toString().trim();
                String img = object.getImage();
                int soluong = i;
                double gia = i * (object.getGia() - (object.getGia() * object.getDiscount() / 100));

                double giadiscount = object.getGia() - (object.getGia() * object.getDiscount() / 100);
       
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FoodModel model = new FoodModel(soluong, name, object.getGia(), img, user.getUid(), giadiscount, gia);

                if (isFoodexits(model)) {

                }

                if (user != null) {
                    Toast.makeText(ShowDetailItemActivity.this, user.getUid() + "", Toast.LENGTH_SHORT).show();
                    FoodDatabase.getInstance(ShowDetailItemActivity.this).foodDao().insertCart(model);
                    Toast.makeText(ShowDetailItemActivity.this, "add sucessfuly", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                } else {
                    Toast.makeText(ShowDetailItemActivity.this, "không tìm thấy user", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // Lấy đối tượng từ Bundle
        FoodModel object = (FoodModel) bundle.getSerializable("object_key");
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                txtgia.setText(i * (object.getGia() - (object.getGia() * object.getDiscount() / 100)) + " VNĐ");
                txtsoluong.setText(String.valueOf(i));
            }
        });
        btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i > 0) {
                    i--;
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        // Lấy đối tượng từ Bundle
                        FoodModel object = (FoodModel) bundle.getSerializable("object_key");
                        txtgia.setText(i * (object.getGia() - (object.getGia() * object.getDiscount() / 100)) + " VNĐ");
                    }

                    txtsoluong.setText(String.valueOf(i));

                }


            }
        });
        // Lấy Intent và Bundle từ Intent gọi Activity đích


        Glide.with(this).load(object.getImage()).into(imgbuttomsheet);
        txtgia.setText(String.valueOf(i * (object.getGia() - (object.getGia() * object.getDiscount() / 100)) + " VNĐ"));
        txtname.setText(object.getName());


    }


    private int tangsoluong(int i) {
        int u = i++;
        return u;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_showdetail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();

        if (i == R.id.giohang) {
            Fragment fragment = new CartFragment();
            FragmentManager fragmentManager = getSupportFragmentManager(); // Hoặc sử dụng getFragmentManager() nếu bạn không sử dụng Support Library
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(android.R.id.content, fragment);
            fragmentTransaction.addToBackStack(null); // Để thêm Activity hiện tại vào back stack
            fragmentTransaction.commit();
        }

        if (i == android.R.id.home) {
            // Xử lý sự kiện nút "Back" ở đây
            onBackPressed(); // Hoặc thực hiện hành động tùy chỉnh của bạn

        }
        return true;
    }

    public void setData() {
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
            txtgiagocdetail.setText(object.getGia() + " VNĐ");
            txtgiagiamdetail.setText(object.getGia() - (object.getGia() * object.getDiscount() / 100) + " VNĐ");
            txtgiamgia.setText("Giảm" + object.getDiscount() + "%");

            if (object.getDiscount() == 0) {
                txtgiamgia.setVisibility(View.GONE);
                txtgiagocdetail.setVisibility(View.GONE);
            }
            if (object.getDiscount() != 0) {
                txtgiagocdetail.setPaintFlags(txtgiagocdetail.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

        }
    }

    public boolean isFoodexits(FoodModel model) {
        List<FoodModel> list = FoodDatabase.getInstance(ShowDetailItemActivity.this).foodDao().checkitemproduct(model.getName());
        return list != null && !list.isEmpty();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        FoodModel object = (FoodModel) bundle.getSerializable("object_key");

        listImage = new ArrayList<>();
        GridLayoutManager manager  =new GridLayoutManager(ShowDetailItemActivity.this , 2);
        recyclerViewlistimage.setLayoutManager(manager);

        imageAdapter = new ListImageProductsAdapter(listImage,ShowDetailItemActivity.this);
        recyclerViewlistimage.setAdapter(imageAdapter);
        String productid = String.valueOf(object.getId());
        String path = String.format("Product_Food/%s/listImage" , productid);

        try {


            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(path);

            databaseRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    String image = dataSnapshot.getValue(String.class);
                    listImage.add(image);
                    imageAdapter.notifyDataSetChanged();


                    // Xử lý dữ liệu ở đây khi có một giá trị mới được thêm vào mảng "listImage"
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    // Xử lý khi có sự thay đổi giá trị trong mảng "listImage"
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    // Xử lý khi có một giá trị bị xóa khỏi mảng "listImage"
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    // Xử lý khi có một giá trị trong mảng "listImage" được di chuyển
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Xử lý lỗi (nếu có)
                }
            });
        } catch (Exception e) {

        }
    }
}