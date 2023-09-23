package com.example.foodorderbasic.Fragnemt;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderbasic.Adapter.FoodBillDetailAdapter;
import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.BillDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

RecyclerView recyclerViewhienthilistbill ;
FoodBillDetailAdapter adapter ;
ArrayList<BillModel> arrayList  = new ArrayList<>();
    public HistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        arrayList = (ArrayList<BillModel>) BillDatabase.getInstance(getContext()).billDAO().getlistBill();

        recyclerViewhienthilistbill = view.findViewById(R.id.rclhienthilistbill);
        LinearLayoutManager manager =new LinearLayoutManager(getContext());
        adapter = new FoodBillDetailAdapter(getContext() , arrayList);

        recyclerViewhienthilistbill.setLayoutManager(manager);
        recyclerViewhienthilistbill.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;




    }

    @SuppressLint("WrongThread")
    @Override
    public void onStart() {
        super.onStart();

    }
}