package com.example.whatsapp_test2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsapp_test2.Adapter.ChatAdapter;
import com.example.whatsapp_test2.Models.SmsModel;
import com.example.whatsapp_test2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatDetailsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    String userName,userPic,reciveID;
    RecyclerView chatRecyclerView;
    ImageView profilePic,back,call,video,menu_icon,sentSMS;
    TextView name;
    EditText SMSText;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);
        getSupportActionBar().hide();

        profilePic = findViewById(R.id.profileImageChat);
        name = findViewById(R.id.userNameChat);
        sentSMS = findViewById(R.id.sentSMS);
        SMSText = findViewById(R.id.SMSText);
        chatRecyclerView = findViewById(R.id.recyclerIdChat);


        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ChatDetailsActivity.this,HomeActivity.class));
                ChatDetailsActivity.this.finish();
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderID = auth.getUid();
        reciveID = getIntent().getStringExtra("userID");
        userName = getIntent().getStringExtra("userName");
        userPic = getIntent().getStringExtra("userPic");

        name.setText(userName);
        Picasso.get().load(userPic).placeholder(R.drawable.avatar).into(profilePic);

        final ArrayList<SmsModel> arrayList = new ArrayList<>();
        final ChatAdapter chatAdapter = new ChatAdapter(arrayList,ChatDetailsActivity.this);
        chatRecyclerView.setAdapter(chatAdapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        

        final String senderRoom = senderID+reciveID;
        final String receiverRoom = reciveID+senderID;

        firebaseDatabase.getReference().child("chat")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                            SmsModel model = dataSnapshot1.getValue(SmsModel.class);
                            arrayList.add(model);
                        }
                        chatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
        sentSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = SMSText.getText().toString();
                final  SmsModel model = new SmsModel(senderID,message);
                model.setTime(new Date().getTime());
                SMSText.setText(" ");
                firebaseDatabase.getReference().child("chat")
                        .child(senderRoom)
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        firebaseDatabase.getReference().child("chat")
                                .child(receiverRoom)
                                .push()
                                .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
//        startActivity(new Intent(ChatDetailsActivity.this,HomeActivity.class));
        finish();
    }
}