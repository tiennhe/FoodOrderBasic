package com.example.foodorderbasic.AdapterAdmin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderbasic.Admin.ShowDetailItemActivityAdmin;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodproductAdapterAdmin extends RecyclerView.Adapter<FoodproductAdapterAdmin.ViewHolder> {
        private Context context ;
        private ArrayList<FoodModel> list = new ArrayList<>() ;





    public FoodproductAdapterAdmin(Context context, ArrayList<FoodModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iterm_food_product , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final  FoodModel model = list.get(position);


        holder.txtname.setText(model.getName());
        holder.txtdiscount.setText("Giảm"+String.valueOf(model.getDiscount())+"%");
        holder.txtgiagoc.setText(String.valueOf(model.getGia()));

        if(model.getDiscount()==0){
            holder.txtdiscount.setVisibility(View.GONE);
            holder.txtgiagoc.setVisibility(View.INVISIBLE);
        }
        holder.txtgiadiscount.setText(String.valueOf(model.getGia()-(model.getDiscount()/100)* model.getGia()));
        Glide.with(context).load(model.getImage()).into(holder.imgfoodproduct);

        if(model.getDiscount()!=0){
                holder.txtgiagoc.setPaintFlags(holder.txtgiagoc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowDetailItemActivityAdmin.class);
// Chuyển sang màn hình đích
                // Đặt đối tượng vào Bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_key", (Serializable) model);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgfoodproduct ;
        TextView txtdiscount , txtname , txtgiagoc , txtgiadiscount ;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgfoodproduct = itemView.findViewById(R.id.imgfoodproduct);
            txtname = itemView.findViewById(R.id.tvname);
            txtgiagoc = itemView.findViewById(R.id.tvgiagoc);
            txtgiadiscount = itemView.findViewById(R.id.tvgiadiscount);
            txtdiscount = itemView.findViewById(R.id.tvDiscount);
            cardView = itemView.findViewById(R.id.carviewitem);
        }
    }
}
