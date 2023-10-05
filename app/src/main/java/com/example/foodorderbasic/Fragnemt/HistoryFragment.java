package com.example.foodorderbasic.Fragnemt;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodorderbasic.Adapter.FoodBillDetailAdapter;
import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.BillDatabase;
import com.example.foodorderbasic.RoomDatabase.FoodDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

RecyclerView recyclerViewhienthilistbill ;
FoodBillDetailAdapter adapter ;
    FirebaseUser user;

    String Uid = "";


    ArrayList<BillModel> arrayList  = new ArrayList<>();
    public HistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerViewhienthilistbill = view.findViewById(R.id.rclhienthilistbill);
        // ...



        return view;




    }

    @SuppressLint("WrongThread")
    @Override
    public void onStart() {
        super.onStart();
      user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            arrayList = (ArrayList<BillModel>) BillDatabase.getInstance(getContext()).billDAO().getlistBillid(user.getUid());
            if(arrayList.isEmpty()){
                Toast.makeText(getContext(), "Đơn hàng rỗng", Toast.LENGTH_SHORT).show();
            }
            LinearLayoutManager manager =new LinearLayoutManager(getContext());
            adapter = new FoodBillDetailAdapter(getContext() , arrayList);
            recyclerViewhienthilistbill.setLayoutManager(manager);
            recyclerViewhienthilistbill.setAdapter(
                    adapter);


            adapter.notifyDataSetChanged();

        }else{
            Toast.makeText(getContext(), "không lấy được uid", Toast.LENGTH_SHORT).show();
        }
    }
}