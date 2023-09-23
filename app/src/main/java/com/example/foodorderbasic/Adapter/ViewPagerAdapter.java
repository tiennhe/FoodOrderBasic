package com.example.foodorderbasic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodorderbasic.Fragnemt.CartFragment;
import com.example.foodorderbasic.Fragnemt.ContactFragnment;
import com.example.foodorderbasic.Fragnemt.FeedbackFragnment;
import com.example.foodorderbasic.Fragnemt.HistoryFragment;
import com.example.foodorderbasic.Fragnemt.HomeFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new CartFragment();
            case 2:
                return new FeedbackFragnment();
            case 3:
                return new ContactFragnment();
            case 4:
                return new HistoryFragment();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
