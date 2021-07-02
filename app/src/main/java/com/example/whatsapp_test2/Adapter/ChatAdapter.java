package com.example.whatsapp_test2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_test2.Models.SmsModel;
import com.example.whatsapp_test2.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<SmsModel> arrayList;
    Context context;

    int senderView = 1;
    int receiverView = 2;

    public ChatAdapter() {

    }
    public ChatAdapter(ArrayList<SmsModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (viewType==senderView){
            View viewS = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new senderViewHolder(viewS);
        }
        else {
            View viewS = LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
            return new receiverViewHolder(viewS);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        SmsModel smsModel = arrayList.get(position);
        if (holder.getClass() == senderViewHolder.class){
            ((senderViewHolder)holder).senderSMS.setText(smsModel.getSms());
//            ((senderViewHolder)holder).senderTime.setText(String.valueOf(smsModel.getTime()));
        }else {
            ((receiverViewHolder)holder).sms_receiver.setText(smsModel.getSms());
//            ((receiverViewHolder)holder).time_receiver.setText(smsModel.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position).getId().equals(FirebaseAuth.getInstance().getUid())){
            return senderView;
        }else {
            return receiverView;
        }
    }

    public class senderViewHolder extends RecyclerView.ViewHolder{
        TextView senderSMS,senderTime;
        public senderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            senderSMS = itemView.findViewById(R.id.senderSMS);
            senderTime = itemView.findViewById(R.id.senderTime);
        }
    }

    public class receiverViewHolder extends RecyclerView.ViewHolder{

        TextView sms_receiver,time_receiver;
        public receiverViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            sms_receiver = itemView.findViewById(R.id.sms_receiver);
            time_receiver = itemView.findViewById(R.id.time_receiver);
        }
    }

}
