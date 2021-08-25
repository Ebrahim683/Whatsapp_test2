package com.example.whatsapp_test2.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_test2.Activity.ChatDetailsActivity;
import com.example.whatsapp_test2.Activity.HomeActivity;
import com.example.whatsapp_test2.Models.UsersModel;
import com.example.whatsapp_test2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserChatAdapter extends RecyclerView.Adapter<UserChatAdapter.ViewHolder> {

    ArrayList<UsersModel> arrayList;
    Context context;
    ActivityOptions options;

    public UserChatAdapter(ArrayList<UsersModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_chats,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserChatAdapter.ViewHolder holder, int position) {
        UsersModel usersModel = arrayList.get(position);
        Picasso.get().load(usersModel.getPic()).placeholder(R.drawable.avatar).into(holder.sampleChatImage);
        holder.sampleUserNameChats.setText(usersModel.getName());

        FirebaseDatabase.getInstance().getReference().child("chat")
                .child(FirebaseAuth.getInstance().getUid()+usersModel.getUserID())
                .orderByChild("time")
                .limitToLast(1)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                holder.sampleLastMessageChats.setText(dataSnapshot.child("sms")
                                        .getValue().toString());
                            }
//                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context, ChatDetailsActivity.class);
                   intent.putExtra("userID", usersModel.getUserID());
                   intent.putExtra("userPic", usersModel.getPic());
                   intent.putExtra("userName", usersModel.getName());
                   options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                           Pair.create(holder.sampleChatImage, "profPic"),
                           Pair.create(holder.sampleUserNameChats, "username")
                   );
                   context.startActivity(intent, options.toBundle());
               }
           });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView sampleChatImage;
        TextView sampleUserNameChats,sampleLastMessageChats;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            sampleChatImage = itemView.findViewById(R.id.sampleChatImage);
            sampleUserNameChats = itemView.findViewById(R.id.sampleUserNameChats);
            sampleLastMessageChats = itemView.findViewById(R.id.sampleLastMessageChats);
        }
    }
}