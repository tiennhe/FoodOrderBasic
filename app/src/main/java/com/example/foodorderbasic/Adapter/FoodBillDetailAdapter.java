package com.example.foodorderbasic.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.BillDatabase;
import com.example.foodorderbasic.RoomDatabase.FoodDatabase;

import java.util.ArrayList;

public class FoodBillDetailAdapter extends RecyclerView.Adapter<FoodBillDetailAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<BillModel> arrayList ;

    public FoodBillDetailAdapter(Context context, ArrayList<BillModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_bill_detaill , parent,  false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BillModel model = arrayList.get(position);

        if(model==null){
            return;
        }

        holder.txtmadonhang.setText(String.valueOf(model.getIdbill()));
        if(model.getStatus_Bill()==0){
            holder.txtstatus.setText("Chờ Xác nhận");
            holder.buttonxacnhandonhang.setVisibility(View.GONE);

        }
        else if(model.getStatus_Bill()==1){
            holder.txtstatus.setText("Đang giao");
            holder.buttonxacnhandonhang.setVisibility(View.VISIBLE);
        }else if(model.getStatus_Bill()==2){
            holder.txtstatus.setText("Đã nhận");
        }


        holder.txthoten.setText(model.getHoten());
        holder.txtdiachi.setText(model.getAdressgiaohang());
        holder.txtngaydathang.setText(model.getDate());
        holder.txttongtien.setText(String.valueOf(model.getTongtien()));
        holder.txtsodienthoai.setText(model.getSodienthoai());

        holder.listproductbilldetail.setText(model.getListsanpham());



        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("Xóa bản ghi").setMessage("Bạn có chắc chắn xóa bản ghi này?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                BillDatabase.getInstance(context).billDAO().deleteBill(model);
                                Toast.makeText(context, "delete successfully", Toast.LENGTH_SHORT).show();
                                arrayList  = (ArrayList<BillModel>) BillDatabase.getInstance(context).billDAO().getlistBill();
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", null).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        if(arrayList!=null){
            return arrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtmadonhang , txthoten , txtdiachi , txtsodienthoai , txtngaydathang , txtstatus , txttongtien , listproductbilldetail;
        RecyclerView rcllistproduct ;
        Button buttonxacnhandonhang , btndelete ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtmadonhang = itemView.findViewById(R.id.txtmadonhangbilldetail);
            txthoten = itemView.findViewById(R.id.txthotenbilldetail);
            txtdiachi = itemView.findViewById(R.id.txtadressbilldetail);
            txtsodienthoai = itemView.findViewById(R.id.txtsdtbilldetail);
            txtngaydathang = itemView.findViewById(R.id.txtdatebilldetail) ;
            txtstatus = itemView.findViewById(R.id.txtststusbilldetail);
            txttongtien = itemView.findViewById(R.id.txttongtienbilldetail);
            buttonxacnhandonhang = itemView.findViewById(R.id.btnxacnhandonhang);

            listproductbilldetail = itemView.findViewById(R.id.txtlistproduct);

            btndelete = itemView.findViewById(R.id.btndelete);


        }
    }
}
