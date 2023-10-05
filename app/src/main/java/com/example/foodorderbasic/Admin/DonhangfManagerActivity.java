package com.example.foodorderbasic.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodorderbasic.AdapterAdmin.ViewPagerAdapterAdminBill;
import com.example.foodorderbasic.Fragnemt.CartFragment;
import com.example.foodorderbasic.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DonhangfManagerActivity extends AppCompatActivity {
        TabLayout mTabLayout ;
        ViewPager2 mviewPager;

        Toolbar toolbar ;

        ViewPagerAdapterAdminBill viewPagerAdapterAdminBill ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhangf_manager);

        mTabLayout = findViewById(R.id.tablayout_bill_admin);
        mviewPager = findViewById(R.id.viewpager_bill_admin);

        toolbar = findViewById(R.id.toolbar_admin_bill);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewPagerAdapterAdminBill = new ViewPagerAdapterAdminBill(DonhangfManagerActivity.this);
        mviewPager.setAdapter(viewPagerAdapterAdminBill);
        new TabLayoutMediator(mTabLayout, mviewPager, (tab, position) -> {
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
}