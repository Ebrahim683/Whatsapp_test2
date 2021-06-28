package com.example.whatsapp_test2.Fragment;

import android.app.ActivityOptions;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsapp_test2.Adapter.UserChatAdapter;
import com.example.whatsapp_test2.Models.UsersModel;
import com.example.whatsapp_test2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ChatFragment extends Fragment {


    ArrayList<UsersModel> arrayList;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    ActivityOptions options;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.rcChat);

        arrayList = new ArrayList<>();
        UserChatAdapter userChatAdapter = new UserChatAdapter(arrayList,getContext());
        recyclerView.setAdapter(userChatAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UsersModel usersModel = dataSnapshot.getValue(UsersModel.class);
                    usersModel.setUserID(dataSnapshot.getKey());
                    arrayList.add(usersModel);
                }
                userChatAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });






        return view;
    }
}