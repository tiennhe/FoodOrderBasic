package com.example.foodorderbasic.AdapterAdmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderbasic.Model.FoodModel;
import com.example.foodorderbasic.Model.UserModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.FoodDatabase;
import com.example.foodorderbasic.RoomDatabase.UserDataBase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserManagerAdapter extends RecyclerView.Adapter<UserManagerAdapter.ViewHolder> {
    ArrayList<UserModel> arrayList ;
    Context context;

    private int count = 0;

    public UserManagerAdapter(ArrayList<UserModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_user_manager , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = arrayList.get(position);
        if(userModel==null){
            return ;
        }



            holder.txtUid.setText(String.valueOf(count));

        holder.txtEmail.setText(userModel.getEmail());
        holder.btndisableacout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("Xóa bản ghi").setMessage("Bạn có chắc chắn xóa user này?")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                UserDataBase.getInstance(context).userDAO().deleteUseer(userModel);
                                Toast.makeText(context, "delete successfully", Toast.LENGTH_SHORT).show();

                                arrayList  = (ArrayList<UserModel>) UserDataBase.getInstance(context).userDAO().getlistUser();
                                notifyDataSetChanged();

                            }
                        }).setNegativeButton("no" , null).show();
            }
        });

        count++;

    }

    @Override
    public int getItemCount() {

        if(!arrayList.isEmpty()){
            return arrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUid , txtEmail ;
        Button btndisableacout ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUid = itemView.findViewById(R.id.tvUid);
            txtEmail = itemView.findViewById(R.id.tvEmail);
            btndisableacout = itemView.findViewById(R.id.btndisableacount);

        }
    }
}
