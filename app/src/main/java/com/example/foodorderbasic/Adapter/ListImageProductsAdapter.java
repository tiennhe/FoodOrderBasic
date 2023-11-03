package com.example.foodorderbasic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderbasic.R;

import java.util.List;

public class ListImageProductsAdapter extends RecyclerView.Adapter<ListImageProductsAdapter.ViewHolder> {
    private List<String> listImage;
    private Context context ;

    public ListImageProductsAdapter(List<String> listImage, Context context) {
        this.listImage = listImage;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_listimage_product , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl = listImage.get(position);
        Glide.with(context).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(listImage!=null){
            return listImage.size();
        }
        return 0;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.listimageproductdetail);
        }
    }
}
