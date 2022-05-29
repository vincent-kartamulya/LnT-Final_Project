package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registButton = findViewById(R.id.register_btn);
        TextView loginTextButton = findViewById(R.id.loginText_btn);

        loginTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                String id = ((EditText)findViewById(R.id.idBimbel_et)).getText().toString();
                String email = ((EditText)findViewById(R.id.emailRegist_et)).getText().toString();
                String nama = ((EditText)findViewById(R.id.namaRegist_et)).getText().toString();
                String pass = ((EditText)findViewById(R.id.passwordRegist_et)).getText().toString();
                String konfirmasi = ((EditText)findViewById(R.id.konfirmasiRegist_et)).getText().toString();

//                validasi
                if(id.isEmpty()){
                    Snackbar.make(view, "Id harus diisi!", Snackbar.LENGTH_LONG).show();
                }else if(email.isEmpty() | !email.contains("@") | !email.endsWith(".com")){
                    Snackbar.make(view, "Email harus diisi dan mengandung '@' serta berakhiran .com", Snackbar.LENGTH_LONG).show();
                }else if(nama.length() < 5){
                    Snackbar.make(view, "Nama minimal 5 huruf!", Snackbar.LENGTH_LONG).show();
                }else if(pass.isEmpty() ){
                    Snackbar.make(view, "Password harus diisi!", Snackbar.LENGTH_LONG).show();
                }else if(!konfirmasi.equals(pass) || konfirmasi.isEmpty()){
                    Snackbar.make(view, "Konfirmasi password harus sama dengan password", Snackbar.LENGTH_LONG).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(
                            RegisterActivity.this, task -> {
                                if(!task.isSuccessful()){
                                    Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show();
                                }else{
                                    User user = new User(nama, email);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(context, "Register Success", Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show();
                                    Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(login);
                                }
                            });
                }
            }
        });

    }
}