package com.example.foodorderbasic.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.BillDatabase;

import java.util.ArrayList;

public class ItemBillAdminAdapter extends RecyclerView.Adapter<ItemBillAdminAdapter.ViewHolder> {
        ArrayList<BillModel> arrayList = new ArrayList<>();
        private Context context;

    public ItemBillAdminAdapter(ArrayList<BillModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_bill_admin , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BillModel model = arrayList.get(position);
        if(model==null){
            return;
        }
        holder.txtmadonhang.setText(String.valueOf(model.getIdbill()));
        holder.txthoten.setText(model.getHoten());
        holder.txtdiachi.setText(model.getAdressgiaohang());
        holder.txtngaydathang.setText(model.getDate());
        holder.txttongtien.setText(String.valueOf(model.getTongtien()));
        holder.txtsodienthoai.setText(model.getSodienthoai());
        holder.listproductbilldetail.setText(model.getListsanpham());


        if(model.getStatus_Bill()==6){
            holder.txtstatusadmin.setText("Đã Hủy");
            holder.buttonxacnhandonhang.setVisibility(View.GONE);
        }
        if(model.getStatus_Bill()==1){
            holder.txtstatusadmin.setText("Đang giao");
            holder.buttonxacnhandonhang.setVisibility(View.GONE);
        }
        if(model.getStatus_Bill()==2){
            holder.txtstatusadmin.setText("Đã Nhận hàng");
            holder.buttonxacnhandonhang.setVisibility(View.GONE);

        }

      holder.buttonxacnhandonhang.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              model.setStatus_Bill(1);
              BillDatabase.getInstance(context).billDAO().updateBill(model);
              notifyDataSetChanged();
          }
      });
    }

    @Override
    public int getItemCount() {

        if(!arrayList.isEmpty()){
            return arrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmadonhang , txthoten , txtdiachi , txtsodienthoai , txtngaydathang , txtstatusadmin , txttongtien , listproductbilldetail;
        RecyclerView rcllistproduct ;
        Button buttonxacnhandonhang , btndelete ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtmadonhang = itemView.findViewById(R.id.txtmadonhangbilldetailadmin);
            txthoten = itemView.findViewById(R.id.txthotenbilldetailadmin);
            txtdiachi = itemView.findViewById(R.id.txtadressbilldetailadmin);
            txtsodienthoai = itemView.findViewById(R.id.txtsdtbilldetailadmin);
            txtngaydathang = itemView.findViewById(R.id.txtdatebilldetailadmin) ;
            txtstatusadmin = itemView.findViewById(R.id.txtststusbilldetail_admin);
            txttongtien = itemView.findViewById(R.id.txttongtienbilldetailadmin);
            buttonxacnhandonhang = itemView.findViewById(R.id.btnxacnhandonhang_admin);

            listproductbilldetail = itemView.findViewById(R.id.txtlistproductadmin);


        }
    }
}
