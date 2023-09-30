package com.example.foodorderbasic.Fragnemt;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderbasic.Activity.ShowDetailItemActivity;
import com.example.foodorderbasic.Adapter.FoodCartAdapter;
import com.example.foodorderbasic.Adapter.FoodproductAdapter;
import com.example.foodorderbasic.Adapter.ListproductBillDetailAdapter;
import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.BillDatabase;
import com.example.foodorderbasic.RoomDatabase.FoodDatabase;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CartFragment extends Fragment {
    Button btndathang ;

    FoodCartAdapter adapter    ;
    ArrayList<FoodModel> list ;

    TextView txttongtien ;
    double tongtiencart = 0;

    RecyclerView recyclerView ;
    public CartFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        btndathang = view.findViewById(R.id.btndathang);
        recyclerView = view.findViewById(R.id.rclCart);
        txttongtien = view.findViewById(R.id.txttongtien);




        list = new ArrayList<>() ;
        list = (ArrayList<FoodModel>) FoodDatabase.getInstance(getContext()).foodDao().getlistItemcart();
//        adapter = new FoodCartAdapter(getContext() , list);

        adapter = new FoodCartAdapter(getContext(), list, new FoodCartAdapter.tongtien() {
            @Override
            public void tongtienChange(double tongtien) {

                tongtiencart=tongtien;
                txttongtien.setText(tongtiencart+"");
            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOpenButtonDilog();
            }
        });
        return view;
    }

    @SuppressLint("MissingInflatedId")
    private void clickOpenButtonDilog() {

        View viewDilog = getLayoutInflater().inflate(R.layout.layout_dialogbuttomdathang , null);
        TextView txterrphuongthuc , txterrhoten , txterrsodienthoai , txterradress ;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(viewDilog);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(false);

        txterrphuongthuc = viewDilog.findViewById(R.id.txterrorphuongthuc);

        txterrhoten = viewDilog.findViewById(R.id.txterrorhoten);
        txterrsodienthoai = viewDilog.findViewById(R.id.txterrorsdt);
        txterradress = viewDilog.findViewById(R.id.txterroradress);

        TextView txttongtienbottomsheet = viewDilog.findViewById(R.id.txttongtienbottomsheet);
        EditText txtphuongthucthanhtoan = viewDilog.findViewById(R.id.edtphuongthuc);
        EditText txthoten = viewDilog.findViewById(R.id.edthotenbill );
        EditText txtsodienthoai = viewDilog.findViewById(R.id.edtsodienthoaibill);
        EditText txtadress = viewDilog.findViewById(R.id.edtadressiaohang);


        txttongtienbottomsheet.setText(tongtiencart+"");
        ArrayList<FoodModel> modelArrayList = new ArrayList<>();
        modelArrayList = (ArrayList<FoodModel>) FoodDatabase.getInstance(getContext()).foodDao().getlistItemcart();
        RecyclerView rcllistproduct = viewDilog.findViewById(R.id.rcllisproduct);
        ListproductBillDetailAdapter billDetailAdapter = new ListproductBillDetailAdapter(list , getContext());

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcllistproduct.setLayoutManager(manager);
        rcllistproduct.setAdapter(billDetailAdapter);
        billDetailAdapter.notifyDataSetChanged();

        Button btnhuy = viewDilog.findViewById(R.id.btnhuy);
        Button buy =viewDilog.findViewById(R.id.btndathang);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phuongthuc = txtphuongthucthanhtoan.getText().toString().trim() ;
                String hoten = txthoten.getText().toString().trim() ;
                String sodienthoai = txtsodienthoai.getText().toString().trim() ;
                String adress = txtadress.getText().toString().trim();
                double tongtien = Double.parseDouble(txttongtienbottomsheet.getText().toString().trim());
                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();

                // Format ngày giờ theo định dạng mong muốn
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(currentDate);

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String formattedTime = timeFormat.format(currentDate);

                String date = formattedDate+" "+formattedTime;

                if(phuongthuc.isEmpty()){
                    txterrphuongthuc.setVisibility(View.VISIBLE);
                }else {
                    txterrphuongthuc.setVisibility(View.GONE);
                }
                if(hoten.isEmpty()){
                    txterrhoten.setVisibility(View.VISIBLE);
                }
                else {
                    txterrhoten.setVisibility(View.GONE);
                }
                if(sodienthoai.isEmpty()){
                    txterrsodienthoai.setVisibility(View.VISIBLE);

                }else {
                    txterrsodienthoai.setVisibility(View.GONE);
                }
                if(adress.isEmpty()){
                    txterradress.setVisibility(View.VISIBLE);
                    return;
                }else {
                    txterradress.setVisibility(View.GONE);

                }
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



                StringBuilder stringBuilder = new StringBuilder();
                for (FoodModel object : list) {
                    stringBuilder.append(object.toString()); // Định nghĩa phương thức toString() trong lớp MyObject
                    stringBuilder.append("\n"); // Thêm ký tự xuống dòng (newline) nếu cần thiết
                }

                String result = stringBuilder.toString();

if(user!=null){
    Toast.makeText(getContext(), user.getUid()+"", Toast.LENGTH_SHORT).show();
    BillModel billModel = new BillModel(  phuongthuc , hoten,sodienthoai , adress , tongtien , 0 , result,3 , user.getUid(),  date);
    BillDatabase.getInstance(getContext()).billDAO().insertBill(billModel);
    Toast.makeText(getContext(), "đặt hàng thành công sucessfuly", Toast.LENGTH_SHORT).show();
    bottomSheetDialog.dismiss();
}else{
    Toast.makeText(getContext(), "không tìm thấy user", Toast.LENGTH_SHORT).show();
}


            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            Toast.makeText(getContext(), user.getUid()+"", Toast.LENGTH_SHORT).show();
            list = new ArrayList<>() ;
            list = (ArrayList<FoodModel>) FoodDatabase.getInstance(getContext()).foodDao().getlistItemcartbyId(user.getUid());

            for (int i = 0; i <list.size() ; i++) {
                tongtiencart+= list.get(i).getGiadiscount()*list.get(i).getQuantity();
            }
            txttongtien.setText(tongtiencart+"");
            adapter = new FoodCartAdapter(getContext(), list, new FoodCartAdapter.tongtien() {
                @Override
                public void tongtienChange(double tongtien) {
                    tongtiencart =tongtien;
                    txttongtien.setText(tongtien+"");
                }
            });
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else{
            Toast.makeText(getContext(), "không có user", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}