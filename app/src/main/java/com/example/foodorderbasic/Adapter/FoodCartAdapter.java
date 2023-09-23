package com.example.foodorderbasic.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.FoodDatabase;

import java.util.ArrayList;

public class FoodCartAdapter  extends  RecyclerView.Adapter<FoodCartAdapter.ViewHolder>{

    private Context context ;
    private ArrayList<FoodModel> arrayList = new ArrayList<>();
    private  tongtien tongtien ;
    double  tongtien1 =0;
    double tongtiengiam = 0;
    public interface tongtien{
            void tongtienChange(double tongtien);
    }

    public FoodCartAdapter(Context context, ArrayList<FoodModel> arrayList, FoodCartAdapter.tongtien tongtien) {
        this.context = context;
        this.arrayList = arrayList;
        this.tongtien = tongtien;
    }

    double gia = 0;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cart , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FoodModel model = arrayList.get(position);

        if(model==null){
            return;
        }
        holder.txtnamecartitem.setText(model.getName());
        holder.txtgiacartitem.setText(model.getGiacartproductitem()+"");
        holder.txtsoluongcartitem.setText(model.getQuantity()+"");
        holder.btntangcartitem.setOnClickListener(new View.OnClickListener() {
            int soluongupdate =model.getQuantity();


            @Override
            public void onClick(View view) {

                soluongupdate+=1;
                gia = model.getGiadiscount()*soluongupdate;
                model.setQuantity(soluongupdate);
                model.setGiacartproductitem(gia);
                FoodDatabase.getInstance(context).foodDao().updateFood(model);
                arrayList = (ArrayList<FoodModel>) FoodDatabase.getInstance(context).foodDao().getlistItemcart();
                for (int i = 0; i < arrayList.size(); i++) {
                    gia = arrayList.get(i).getGiadiscount()*arrayList.get(i).getQuantity();
                    tongtien1+=gia;
                }
                tongtien.tongtienChange(tongtien1);
                tongtien1 = 0;
                notifyDataSetChanged();


            }
        });
        holder.btngiamcartitem.setOnClickListener(new View.OnClickListener() {

            int soluongupdate =model.getQuantity();

            @Override
            public void onClick(View view) {
                tongtien1 = 0;
            if(model.getQuantity()>0){
            soluongupdate-=1;
            gia  =model.getGiadiscount()*soluongupdate;
            model.setQuantity(soluongupdate);
            model.setGiacartproductitem(gia);

            FoodDatabase.getInstance(context).foodDao().updateFood(model);
            arrayList = (ArrayList<FoodModel>) FoodDatabase.getInstance(context).foodDao().getlistItemcart();
            for (int i = 0; i < arrayList.size(); i++) {
                gia = arrayList.get(i).getGiadiscount()*arrayList.get(i).getQuantity();
                tongtien1+=gia;
            }
            tongtien.tongtienChange(tongtien1);
            tongtien1 = 0;
            notifyDataSetChanged();
        }
            }
        });

        holder.btndeleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("Xóa bản ghi").setMessage("Bạn có chắc chắn xóa bản ghi này?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                tongtien1 = 0;
                            FoodDatabase.getInstance(context).foodDao().deletaFood(model);
                            Toast.makeText(context, "delete successfully", Toast.LENGTH_SHORT).show();
                            arrayList  = (ArrayList<FoodModel>) FoodDatabase.getInstance(context).foodDao().getlistItemcart();

                            if(arrayList==null){
                                tongtien1 = 0 ;
                            }
                                for (int j = 0; j <arrayList.size() ; j++) {
                                   tongtien1+= arrayList.get(j).getGiadiscount()*arrayList.get(j).getQuantity();
                                }
                                tongtien.tongtienChange(tongtien1);
                            notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", null).show();
            }
        });
        Glide.with(context).load(model.getImage()).into(holder.imgcartitem);

    }

    @Override
    public int getItemCount() {

        if(arrayList!=null){
            return arrayList.size();
        }
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

         ImageView imgcartitem ;
         Button btngiamcartitem   , btntangcartitem , btndeleteitem ;
         TextView txtsoluongcartitem  ,txtnamecartitem ,txtgiacartitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgcartitem = itemView.findViewById(R.id.imgitemcart);
            btngiamcartitem = itemView.findViewById(R.id.btngiamcart) ;
            btntangcartitem = itemView.findViewById(R.id.btntangcart);
            btndeleteitem = itemView.findViewById(R.id.btndeleteitemcart);
            txtgiacartitem = itemView.findViewById(R.id.txtgiacart);
            txtsoluongcartitem = itemView.findViewById(R.id.txtquantitycart) ;
            txtnamecartitem = itemView.findViewById(R.id.txtnameitemcart);
        }
    }
}
