package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLoginMain = (Button)findViewById(R.id.loginMain_btn);
        Button btnRegistMain = (Button)findViewById(R.id.registerMain_btn);

//        buat ke halaman login
        btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });

//        buat ke halaman register
        btnRegistMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegist = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentRegist);
            }
        });
    }

}