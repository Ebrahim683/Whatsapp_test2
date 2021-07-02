package com.example.whatsapp_test2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsapp_test2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupChatActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    String userName,userPic,reciveID;
    RecyclerView chatRecyclerViewG;
    ImageView profilePicG,backG,callG,videoG,menu_iconG,sentSMSG;
    TextView nameG;
    EditText SMSTextG;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        profilePicG = findViewById(R.id.profileImageChatG);
        nameG = findViewById(R.id.userNameChatG);
        sentSMSG = findViewById(R.id.sentSMSG);
        SMSTextG = findViewById(R.id.SMSTextG);
        chatRecyclerViewG = findViewById(R.id.recyclerIdChatG);


        findViewById(R.id.imgBackG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ChatDetailsActivity.this,HomeActivity.class));
                GroupChatActivity.this.finish();
            }
        });

    }
}