package com.example.foodorderbasic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodorderbasic.Fragnemt.ChoxacnhanFragnment;
import com.example.foodorderbasic.Fragnemt.DahuyFragnment;
import com.example.foodorderbasic.Fragnemt.DanhanFragnment;
import com.example.foodorderbasic.FragnmentAdmin.Bill_Fragnment_Da_Huy;
import com.example.foodorderbasic.FragnmentAdmin.Bill_Fragnment_Da_Nhan;
import com.example.foodorderbasic.FragnmentAdmin.Bill_Fragnment_Xac_Nhan;

public class ViewPagerAdapterBillUser extends FragmentStateAdapter {
    public ViewPagerAdapterBillUser(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ChoxacnhanFragnment();


            case 1:
                return new DanhanFragnment();

            case 2:
                return new DahuyFragnment();

            default:
                return new ChoxacnhanFragnment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
