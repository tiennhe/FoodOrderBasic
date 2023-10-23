package com.example.foodorderbasic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderbasic.Model.ChatMessageModel;
import com.example.foodorderbasic.R;

import java.util.ArrayList;

public class ChatMessageAdapter extends  RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<ChatMessageModel> arrayList ;

    public ChatMessageAdapter(Context context, ArrayList<ChatMessageModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_item , parent , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessageModel model = arrayList.get(position);
        if(model==null){
            return;
        }
        holder.txtchatmessage.setText(model.getMessage());
    }

    @Override
    public int getItemCount() {
        if(!arrayList.isEmpty()){
            return arrayList.size();
        }
        return 0;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        TextView txtchatmessage ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtchatmessage = itemView.findViewById(R.id.messageTextView);
        }
    }
}
