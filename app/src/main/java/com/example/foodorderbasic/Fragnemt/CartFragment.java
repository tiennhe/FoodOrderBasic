package com.example.foodorderbasic.Fragnemt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
import android.widget.RadioButton;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import vn.momo.momo_partner.AppMoMoLib;


public class CartFragment extends Fragment {
    Button btndathang;

    FoodCartAdapter adapter;
    ArrayList<FoodModel> list;

    TextView txttongtien;
    double tongtiencart = 0;
    String phuongthuc = "";

    RecyclerView recyclerView;
    private String amount = "10000";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "HoangNgoc";
    private String merchantCode = "MOMOC2IC20220610";
    private String merchantNameLabel = "Nhà cung cấp";
    private String description = "Thanh toán đon hàng của bạn";


    public CartFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT); // AppMoMoLib.ENVIRONMENT.PRODUCTION
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btndathang = view.findViewById(R.id.btndathang);
        recyclerView = view.findViewById(R.id.rclCart);
        txttongtien = view.findViewById(R.id.txttongtien);


        list = new ArrayList<>();
        list = (ArrayList<FoodModel>) FoodDatabase.getInstance(getContext()).foodDao().getlistItemcart();
//        adapter = new FoodCartAdapter(getContext() , list);

        adapter = new FoodCartAdapter(getContext(), list, new FoodCartAdapter.tongtien() {
            @Override
            public void tongtienChange(double tongtien) {

                tongtiencart = tongtien;
                txttongtien.setText(tongtiencart + "");
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

        View viewDilog = getLayoutInflater().inflate(R.layout.layout_dialogbuttomdathang, null);
        TextView txterrphuongthuc, txterrhoten, txterrsodienthoai, txterradress;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(viewDilog);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(false);

        txterrphuongthuc = viewDilog.findViewById(R.id.txterrorphuongthuc);

        txterrhoten = viewDilog.findViewById(R.id.txterrorhoten);
        txterrsodienthoai = viewDilog.findViewById(R.id.txterrorsdt);
        txterradress = viewDilog.findViewById(R.id.txterroradress);

        TextView txttongtienbottomsheet = viewDilog.findViewById(R.id.txttongtienbottomsheet);
        RadioButton rdotienmat, rdomomo;
        rdotienmat = viewDilog.findViewById(R.id.rdobttienmat);

        rdomomo = viewDilog.findViewById(R.id.rdobtmomo);
        EditText txthoten = viewDilog.findViewById(R.id.edthotenbill);
        EditText txtsodienthoai = viewDilog.findViewById(R.id.edtsodienthoaibill);
        EditText txtadress = viewDilog.findViewById(R.id.edtadressiaohang);


        txttongtienbottomsheet.setText(tongtiencart + "");
        ArrayList<FoodModel> modelArrayList = new ArrayList<>();
        modelArrayList = (ArrayList<FoodModel>) FoodDatabase.getInstance(getContext()).foodDao().getlistItemcart();
        RecyclerView rcllistproduct = viewDilog.findViewById(R.id.rcllisproduct);
        ListproductBillDetailAdapter billDetailAdapter = new ListproductBillDetailAdapter(list, getContext());

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcllistproduct.setLayoutManager(manager);
        rcllistproduct.setAdapter(billDetailAdapter);
        billDetailAdapter.notifyDataSetChanged();

        Button btnhuy = viewDilog.findViewById(R.id.btnhuy);
        Button buy = viewDilog.findViewById(R.id.btndathang);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rdomomo.isChecked()){
                    phuongthuc = "Chuyển khoản qua MOMO";
                    String hoten = txthoten.getText().toString().trim();
                    String sodienthoai = txtsodienthoai.getText().toString().trim();
                    String adress = txtadress.getText().toString().trim();
                    double tongtien = Double.parseDouble(txttongtienbottomsheet.getText().toString().trim());
                    Calendar calendar = Calendar.getInstance();
                    Date currentDate = calendar.getTime();

                    // Format ngày giờ theo định dạng mong muốn
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String formattedDate = dateFormat.format(currentDate);

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    String formattedTime = timeFormat.format(currentDate);

                    String date = formattedDate;

                    if (phuongthuc.isEmpty()) {
                        txterrphuongthuc.setVisibility(View.VISIBLE);
                    } else {
                        txterrphuongthuc.setVisibility(View.GONE);
                    }
                    if (hoten.isEmpty()) {
                        txterrhoten.setVisibility(View.VISIBLE);
                    } else {
                        txterrhoten.setVisibility(View.GONE);
                    }
                    if (sodienthoai.isEmpty()) {
                        txterrsodienthoai.setVisibility(View.VISIBLE);

                    } else {
                        txterrsodienthoai.setVisibility(View.GONE);
                    }
                    if (adress.isEmpty()) {
                        txterradress.setVisibility(View.VISIBLE);
                        return;
                    } else {
                        txterradress.setVisibility(View.GONE);

                    }
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                    StringBuilder stringBuilder = new StringBuilder();
                    for (FoodModel object : list) {
                        stringBuilder.append(object.toString()); // Định nghĩa phương thức toString() trong lớp MyObject
                        stringBuilder.append("\n"); // Thêm ký tự xuống dòng (newline) nếu cần thiết
                    }

                    String result = stringBuilder.toString();
                    Toast.makeText(getContext(), phuongthuc, Toast.LENGTH_SHORT).show();

                    if (user != null) {
//                        Toast.makeText(getContext(), user.getUid() + "", Toast.LENGTH_SHORT).show();
//                        BillModel billModel = new BillModel(phuongthuc, hoten, sodienthoai, adress, tongtien, 0, result, 3, user.getUid(), date);
//                        BillDatabase.getInstance(getContext()).billDAO().insertBill(billModel);
//                        Toast.makeText(getContext(), "đặt hàng thành công sucessfuly", Toast.LENGTH_SHORT).show();
                        BillModel model = BillDatabase.getInstance(getContext()).billDAO().getLastBill();
                        requestPayment(model.getIdbill());
                        bottomSheetDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "không tìm thấy user", Toast.LENGTH_SHORT).show();
                    }
                }
                if(rdotienmat.isChecked()){
                    phuongthuc = "Tiền mặt";
                    String hoten = txthoten.getText().toString().trim();
                    String sodienthoai = txtsodienthoai.getText().toString().trim();
                    String adress = txtadress.getText().toString().trim();
                    double tongtien = Double.parseDouble(txttongtienbottomsheet.getText().toString().trim());
                    Calendar calendar = Calendar.getInstance();
                    Date currentDate = calendar.getTime();

                    // Format ngày giờ theo định dạng mong muốn
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String formattedDate = dateFormat.format(currentDate);

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    String formattedTime = timeFormat.format(currentDate);

                    String date = formattedDate;

                    if (phuongthuc.isEmpty()) {
                        txterrphuongthuc.setVisibility(View.VISIBLE);
                    } else {
                        txterrphuongthuc.setVisibility(View.GONE);
                    }
                    if (hoten.isEmpty()) {
                        txterrhoten.setVisibility(View.VISIBLE);
                    } else {
                        txterrhoten.setVisibility(View.GONE);
                    }
                    if (sodienthoai.isEmpty()) {
                        txterrsodienthoai.setVisibility(View.VISIBLE);

                    } else {
                        txterrsodienthoai.setVisibility(View.GONE);
                    }
                    if (adress.isEmpty()) {
                        txterradress.setVisibility(View.VISIBLE);
                        return;
                    } else {
                        txterradress.setVisibility(View.GONE);

                    }
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                    StringBuilder stringBuilder = new StringBuilder();
                    for (FoodModel object : list) {
                        stringBuilder.append(object.toString()); // Định nghĩa phương thức toString() trong lớp MyObject
                        stringBuilder.append("\n"); // Thêm ký tự xuống dòng (newline) nếu cần thiết
                    }

                    String result = stringBuilder.toString();
                    Toast.makeText(getContext(), phuongthuc, Toast.LENGTH_SHORT).show();

                    if (user != null) {

                        BillModel billModel = new BillModel(phuongthuc, hoten, sodienthoai, adress, tongtien, 0, result, 3, user.getUid(), date);
                        BillDatabase.getInstance(getContext()).billDAO().insertBill(billModel);
                        Toast.makeText(getContext(), "đặt hàng thành công sucessfuly", Toast.LENGTH_SHORT).show();
                        list.clear();

                        FoodDatabase.getInstance(getContext()).foodDao().deleteById(user.getUid());
                        adapter.notifyDataSetChanged();
                        bottomSheetDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "không tìm thấy user", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Toast.makeText(getContext(), user.getUid() + "", Toast.LENGTH_SHORT).show();
            list = new ArrayList<>();
            list = (ArrayList<FoodModel>) FoodDatabase.getInstance(getContext()).foodDao().getlistItemcartbyId(user.getUid());

            for (int i = 0; i < list.size(); i++) {
                tongtiencart += list.get(i).getGiadiscount() * list.get(i).getQuantity();
            }
            txttongtien.setText(tongtiencart + "");
            adapter = new FoodCartAdapter(getContext(), list, new FoodCartAdapter.tongtien() {
                @Override
                public void tongtienChange(double tongtien) {
                    tongtiencart = tongtien;
                    txttongtien.setText(tongtien + "");
                }
            });
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else {
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
    //Get token through MoMo app
    private void requestPayment(int idbill) {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
        if (txttongtien.getText().toString() != null && txttongtien.getText().toString().trim().length() != 0)
            amount = txttongtien.getText().toString().trim();

        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put("merchantname", merchantName); //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
        eventValue.put("merchantcode", merchantCode); //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
        eventValue.put("amount", amount); //Kiểu integer
        eventValue.put("orderId", String.valueOf(idbill)); //uniqueue id cho Bill order, giá trị duy nhất cho mỗi đơn hàng
        eventValue.put("orderLabel", String.valueOf(idbill)); //gán nhãn

        //client Optional - bill info
        eventValue.put("merchantnamelabel", "Dịch vụ");//gán nhãn
        eventValue.put("fee", "0"); //Kiểu integer
        eventValue.put("description", description); //mô tả đơn hàng - short description

        //client extra data
        eventValue.put("requestId",  merchantCode+"merchant_billId_"+System.currentTimeMillis());
        eventValue.put("partnerCode", merchantCode);
        //Example extra data
        JSONObject objExtraData = new JSONObject();
        try {
            objExtraData.put("site_code", "008");
            objExtraData.put("site_name", "CGV Cresent Mall");
            objExtraData.put("screen_code", 0);
            objExtraData.put("screen_name", "Special");
            objExtraData.put("movie_name", "Kẻ Trộm Mặt Trăng 3");
            objExtraData.put("movie_format", "2D");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eventValue.put("extraData", objExtraData.toString());

        eventValue.put("extra", "");
        AppMoMoLib.getInstance().requestMoMoCallBack((Activity) getContext(), eventValue);


    }
    //Get token callback from MoMo app an submit to server side
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if(data != null) {
                if(data.getIntExtra("status", -1) == 0) {
                    //TOKEN IS AVAILABLE

                    Log.d("thnahcong", data.getStringExtra("message"));

                    String token = data.getStringExtra("data"); //Token response
                    String phoneNumber = data.getStringExtra("phonenumber");
                    String env = data.getStringExtra("env");
                    if(env == null){
                        env = "app";
                    }

                    if(token != null && !token.equals("")) {
                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                        // IF Momo topup success, continue to process your order
                    } else {
                        Log.d("thnahcong", data.getStringExtra("message"));
                    }
                } else if(data.getIntExtra("status", -1) == 1) {
                    //TOKEN FAIL
                    String message = data.getStringExtra("message") != null?data.getStringExtra("message"):"Thất bại";
                    Log.d("thnahcong", data.getStringExtra("message"));
                } else if(data.getIntExtra("status", -1) == 2) {
                    //TOKEN FAIL
                    Log.d("thnahcong", data.getStringExtra("message"));
                } else {
                    //TOKEN FAIL
                    Log.d("thnahcong", data.getStringExtra("message"));
                }
            } else {
                Log.d("thnahcong", data.getStringExtra("message"));
            }
        } else {
            Log.d("thnahcong", data.getStringExtra("message"));
        }
    }
}