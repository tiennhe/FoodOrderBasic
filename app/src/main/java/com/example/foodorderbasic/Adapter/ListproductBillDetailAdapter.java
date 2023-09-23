package com.example.foodorderbasic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;

import java.util.ArrayList;

public class ListproductBillDetailAdapter extends RecyclerView.Adapter<ListproductBillDetailAdapter.ViewHolder>{

    private  ArrayList<FoodModel> arrayList ;
    private Context context ;

    public ListproductBillDetailAdapter(ArrayList<FoodModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_bottomsheet_cartproduct , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FoodModel model = arrayList.get(position);

        if(model==null){
            return ;
        }
        holder.txtnamebilldetail.setText("-"+model.getName()+"(");
        holder.txtsoluongbilldetail.setText("-Số lượng:"+model.getQuantity());
        holder.txtgiabilldetail.setText(" "+String.valueOf(model.getQuantity()*(model.getGia()-(model.getDiscount()/100)*model.getGia()))+" VNĐ)");

    }

    @Override
    public int getItemCount() {

        if(arrayList!=null){
            return arrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnamebilldetail , txtgiabilldetail , txtsoluongbilldetail ;
        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            txtnamebilldetail = itemView.findViewById(R.id.txtnamebill) ;
            txtgiabilldetail = itemView.findViewById(R.id.txtgiabill) ;
            txtsoluongbilldetail = itemView.findViewById(R.id.txtquantitybill);
        }
    }
}
