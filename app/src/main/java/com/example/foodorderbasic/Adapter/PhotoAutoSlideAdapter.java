package com.example.foodorderbasic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.foodorderbasic.Model.PhotoHeader;
import com.example.foodorderbasic.R;

import java.util.List;

public class PhotoAutoSlideAdapter extends RecyclerView.Adapter<PhotoAutoSlideAdapter.ViewHolder> {


    private Context context ;
    private List<PhotoHeader> list ;

    public PhotoAutoSlideAdapter(Context context, List<PhotoHeader> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_photo , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        PhotoHeader photoHeader = list.get(position);
        if(photoHeader==null){
            return;
        }


        holder.imageView.setImageResource(photoHeader.getResourceId());
    }

    @Override
    public int getItemCount() {

        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgslideauto);
        }
    }


}
