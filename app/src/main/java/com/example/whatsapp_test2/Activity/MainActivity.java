package com.example.whatsapp_test2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsapp_test2.Models.UsersModel;
import com.example.whatsapp_test2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    EditText nameET,emailET,passET;
    String name,email,pass;
    Button btnS;
    TextView lgT;
    UsersModel users;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.passwordET);
        lgT = findViewById(R.id.lgIN);
        btnS = findViewById(R.id.signUpID);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        lgT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });


    }

    public void signUp(){
        email = emailET.getText().toString();
        pass = passET.getText().toString();
        name = nameET.getText().toString();

        if (email.isEmpty()||pass.isEmpty()||name.isEmpty()){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Error Email", Toast.LENGTH_SHORT).show();
        }else {
            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();
            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()){
                        users = new UsersModel(name,email,pass);
                        id = task.getResult().getUser().getUid();
                        firebaseDatabase.getReference().child("Users").child(id).setValue(users);
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                        finish();
                    }else {
                        Log.e("error1","Error = "+task.getException().getMessage());
                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }




}