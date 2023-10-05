package com.example.foodorderbasic.FragnmentAdmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderbasic.AdapterAdmin.ItemBillAdminAdapter;
import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.BillDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bill_Fragnment_Xac_Nhan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bill_Fragnment_Xac_Nhan extends Fragment {

private RecyclerView recyclerView ;
private ArrayList<BillModel> arrayList ;

private ItemBillAdminAdapter adapter;

    public Bill_Fragnment_Xac_Nhan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_bill__fragnment__xac__nhan, container, false);
        recyclerView = view.findViewById(R.id.rclxacnhandonadmin);
        arrayList = new ArrayList<>();
        arrayList = (ArrayList<BillModel>) BillDatabase.getInstance(getContext()).billDAO().getlistBillbyStatus(0);
        adapter = new ItemBillAdminAdapter(arrayList , getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}