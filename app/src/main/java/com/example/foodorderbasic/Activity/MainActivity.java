package com.example.foodorderbasic.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.foodorderbasic.Adapter.ViewPagerAdapter;
import com.example.foodorderbasic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int PUBLIC_REQUEST_CODE = 10;
    private static MainActivity instance;

        ViewPager2 viewPager2 ;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        instance = this;
         navigationView = findViewById(R.id.btnavigation);
        viewPager2 = findViewById(R.id.viewpager2);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);

        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.action_cart).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.action_feedback).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.action_contact).setChecked(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.action_history).setChecked(true);
                        break;

                }
                super.onPageSelected(position);
            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() ==R.id.action_home){
                    viewPager2.setCurrentItem(0);
                }
                 if(item.getItemId() ==R.id.action_cart){
                    viewPager2.setCurrentItem(1);
                }
                 if(item.getItemId() ==R.id.action_feedback){
                    viewPager2.setCurrentItem(2);
                }
                 if(item.getItemId() ==R.id.action_contact){
                    viewPager2.setCurrentItem(3);
                }
                 if(item.getItemId() ==R.id.action_history){
                    viewPager2.setCurrentItem(4);
                }
                return true;
            }
        });
    }


    public static MainActivity getInstance() {
        return instance;
    }

}