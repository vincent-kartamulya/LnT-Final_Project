package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.login_btn);
        TextView regisTextButton = findViewById(R.id.registerText_btn);

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser mFirebaseuser = mAuth.getCurrentUser();
            if(mFirebaseuser != null){
                Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                Intent counter = new Intent(LoginActivity.this, CounterActivity.class);
                startActivity(counter);
            }else{
                Toast.makeText(LoginActivity.this, "Cannot logged in", Toast.LENGTH_SHORT).show();
            }
        };

//        kalau registrasi
        regisTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regist = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(regist);
            }
        });

//        kalau login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText)findViewById(R.id.emailLogin_et)).getText().toString();
                String password = ((EditText)findViewById(R.id.passwordRegist_et)).getText().toString();

                if(email.length() < 1 || !email.contains("@") || !email.endsWith(".com")){
                    Snackbar.make(view, "Email harus diisi dan mengandung '@' serta berakhiran .com", Snackbar.LENGTH_LONG).show();
                }else if(password.length() < 1){
                    Snackbar.make(view, "Password harus diisi!", Snackbar.LENGTH_LONG).show();
                }else{
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                            LoginActivity.this, task -> {
                                if(!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Login Error, Please Try Again", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    Intent counter = new Intent(LoginActivity.this, CounterActivity.class);
                                    startActivity(counter);
                                }
                            }
                    );
                }
            }
        });
    }

    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}