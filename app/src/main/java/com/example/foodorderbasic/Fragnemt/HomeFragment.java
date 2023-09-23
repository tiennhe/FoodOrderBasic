package com.example.foodorderbasic.Fragnemt;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodorderbasic.Adapter.FoodproductAdapter;
import com.example.foodorderbasic.Adapter.PhotoAutoSlideAdapter;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.Model.PhotoHeader;
import com.example.foodorderbasic.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment {

private ViewPager2 viewPager ;
private CircleIndicator3 indicator ;
private PhotoAutoSlideAdapter photoAutoSlideAdapter ;

private Button button ;
private FoodproductAdapter adapter ;
private RecyclerView recyclerView ;
private List<PhotoHeader> list = new ArrayList<>() ;
private ArrayList<FoodModel> arrayList = new ArrayList<>();
private Handler handler = new Handler() ;
private Runnable runnable = new Runnable() {
    @Override
    public void run() {

        if(viewPager.getCurrentItem()==list.size()-1){
            viewPager.setCurrentItem(0);
        }else{
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        }

    }
};

    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycleview);
        viewPager = view.findViewById(R.id.viewpagerSlideauto) ;
        indicator = view.findViewById(R.id.circle_center);

        getdataFormFirebase();


        adapter = new FoodproductAdapter(getContext()  ,arrayList);

        GridLayoutManager manager = new GridLayoutManager(getContext() , 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);



        list = getListPhoto();
        photoAutoSlideAdapter  = new PhotoAutoSlideAdapter(getContext() , list);


        viewPager.setAdapter(photoAutoSlideAdapter);
        indicator.setViewPager(viewPager);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);

                handler.postDelayed(runnable , 3000);
            }
        });


        return view;
    }



    private List<PhotoHeader> getListPhoto() {

        List<PhotoHeader> list = new ArrayList<>();

        list.add(new PhotoHeader(R.drawable.banhxeo));
        list.add(new PhotoHeader(R.drawable.comtam));
        list.add(new PhotoHeader(R.drawable.metdoan));
        list.add(new PhotoHeader(R.drawable.suonxaochuanot));


        return list;
    }

    @Override
    public void onPause() {


        super.onPause();

        handler.removeCallbacks(runnable);

    }

    @Override
    public void onStart() {
        super.onStart();


    }

        private void getdataFormFirebase() {
           try {
               FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;

               DatabaseReference databaseReference = firebaseDatabase.getReference("Product_Food");


               Log.d("url", "getdataFormFirebase: "+databaseReference);

               databaseReference.addChildEventListener(new ChildEventListener() {
                   @Override
                   public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                       FoodModel model = snapshot.getValue(FoodModel.class);
                       if(model!=null){
                           arrayList.add(model);
                           adapter.notifyDataSetChanged();
                       }
                       adapter.notifyDataSetChanged();

                   }



                   @Override
                   public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                       FoodModel model = snapshot.getValue(FoodModel.class);
                       if( model==null||arrayList==null|| arrayList.isEmpty()){
                        return;
                       }

                       for (int i = 0; i < arrayList.size(); i++) {
                           if(model.getId() ==arrayList.get(i).getId()){
                               arrayList.set(i , model);
                           }
                           
                       }

                       adapter.notifyDataSetChanged();

                   }

                   @Override
                   public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                   }

                   @Override
                   public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
           }catch (Exception e){
               Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
           }
        }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable , 3000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}