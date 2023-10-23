package com.example.foodorderbasic.Fragnemt;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodorderbasic.Adapter.FoodBillDetailAdapter;
import com.example.foodorderbasic.Adapter.ViewPagerAdapterBillUser;
import com.example.foodorderbasic.AdapterAdmin.ViewPagerAdapterAdminBill;
import com.example.foodorderbasic.Admin.DonhangfManagerActivity;
import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.BillDatabase;
import com.example.foodorderbasic.RoomDatabase.FoodDatabase;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

//RecyclerView recyclerViewhienthilistbill ;
//FoodBillDetailAdapter adapter ;
//    FirebaseUser user;
//
//    String Uid = "";
//
//
//    ArrayList<BillModel> arrayList  = new ArrayList<>();

    ViewPager2 viewPager2 ;
    ViewPagerAdapterBillUser adapterBillUser ;
    TabLayout mTabLayout;
    public HistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_history, container, false);
//        recyclerViewhienthilistbill = view.findViewById(R.id.rclhienthilistbill);
        // ...

        viewPager2 = view.findViewById(R.id.viewpager_bill_user);
        mTabLayout = view.findViewById(R.id.tablayout_bill_user);


        adapterBillUser = new ViewPagerAdapterBillUser(HistoryFragment.this);
        viewPager2.setAdapter(adapterBillUser);
        new TabLayoutMediator(mTabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Xác Nhận đơn hàng");
                    break;
                case 1:
                    tab.setText("Đã nhận");
                    break;
                case 2:
                    tab.setText("Đã hủy");
                    break;


            }
        }).attach();




        return view;




    }

    @SuppressLint("WrongThread")
    @Override
    public void onStart() {
        super.onStart();
//      user= FirebaseAuth.getInstance().getCurrentUser();
//        if(user!=null){
//            arrayList = (ArrayList<BillModel>) BillDatabase.getInstance(getContext()).billDAO().getlistBillid(user.getUid());
//            if(arrayList.isEmpty()){
//                Toast.makeText(getContext(), "Đơn hàng rỗng", Toast.LENGTH_SHORT).show();
//            }
//            LinearLayoutManager manager =new LinearLayoutManager(getContext());
//            adapter = new FoodBillDetailAdapter(getContext() , arrayList);
//            recyclerViewhienthilistbill.setLayoutManager(manager);
//            recyclerViewhienthilistbill.setAdapter(
//                    adapter);
//            adapter.notifyDataSetChanged();
//
//        }else{
//            Toast.makeText(getContext(), "không lấy được uid", Toast.LENGTH_SHORT).show();
//        }
    }
}