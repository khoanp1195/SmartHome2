package com.example.firebasetest;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.firebasetest.Login1.Login;
import com.example.firebasetest.Login1.Register;
import com.google.android.material.tabs.TabItem;


public class Sanh extends AppCompatActivity {


    Button main,history,chart,btn;
    TextView TxtFullName;
   // TabItem profile;
    ImageView general, signin;
    TabItem profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sanh);

       general= findViewById(R.id.Signup);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sanh.this, Register.class);
                startActivity(intent);
            }
        });
        signin= findViewById(R.id.signIn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sanh.this, Login.class);
                startActivity(intent);
            }
        });

        /*
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sanh.this, RankScore2.class);
                startActivity(intent);
            }
        });

        TxtFullName = findViewById(R.id.txtFullName);
        TxtFullName.setText("Hello \t" + CurrentUser.currentUser.getName());

        main = findViewById(R.id.main_ac);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sanh.this, MainActivity.class);
                startActivity(intent);
            }
        });
        history = findViewById(R.id.btnHistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sanh.this, list.class);
                startActivity(intent);
            }

        });
        chart = findViewById(R.id.btnchart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sanh.this, Bieudo.class);
                startActivity(intent);
            }

        });


         */
    }}
