package com.example.foodorderbasic.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodorderbasic.Adapter.FoodCartAdapter;
import com.example.foodorderbasic.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderbasic.Model.BillModel;

import java.util.ArrayList;

public class DoanhthuAdapter extends RecyclerView.Adapter<DoanhthuAdapter.ViewHolder> {
    private ArrayList<BillModel>arrayList  ;

    private Context context ;
    private tongtienbilladmin tongtienbilladmin ;

    double  tongtien =0;

    public interface tongtienbilladmin{
        void tongtiendoanhthuadmin(double tongtien);
    }

    public DoanhthuAdapter(ArrayList<BillModel> arrayList, Context context, DoanhthuAdapter.tongtienbilladmin tongtienbilladmin) {
        this.arrayList = arrayList;
        this.context = context;
        this.tongtienbilladmin = tongtienbilladmin;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_doanhthu_admin,parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BillModel model = arrayList.get(position);
        if(model==null){
            tongtien = 0;
            tongtienbilladmin.tongtiendoanhthuadmin(tongtien);
            return;
        }

        holder.txtidBill.setText(String.valueOf(model.getIdbill()));
        holder.txtthucdonbill.setText(model.getListsanpham());
        holder.txttongtienbill.setText(String.valueOf(model.getTongtien()));

        for (int i = 0; i <arrayList.size() ; i++) {
            tongtien +=arrayList.get(i).getTongtien();
        }

        tongtienbilladmin.tongtiendoanhthuadmin(tongtien);
        tongtien = 0;


    }

    @Override
    public int getItemCount() {

        if(!arrayList.isEmpty()){
            return arrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtidBill , txtthucdonbill , txttongtienbill;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtidBill = itemView.findViewById(R.id.tvidBilldoanhthu);
            txtthucdonbill = itemView.findViewById(R.id.tvthucdondoanhthu);
            txttongtienbill = itemView.findViewById(R.id.tvtongtienbilldoanhthu);
        }
    }
}
