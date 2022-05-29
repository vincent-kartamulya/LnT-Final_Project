package com.example.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class CounterFragment extends Fragment {
    private TextView counterNum;
    private int num;
    private Button btnPlus, btnMin, btnReset;

    public CounterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_counter, container, false);

        SharedPreferences sp = getActivity().getSharedPreferences("angkaTerakhir", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        counterNum = view.findViewById(R.id.counter);
        btnPlus = view.findViewById(R.id.plus_btn);
        btnMin = view.findViewById(R.id.minus_btn);
        btnReset = view.findViewById(R.id.reset_btn);

//        buat ngurangin dan masukin ke shared preferences
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                counterNum.setText(String.valueOf(num));
                edit.putInt("counterAngka", num);
                edit.apply();
            }
        });

//      buat nambahin dan masukin ke shared preferences
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                counterNum.setText(String.valueOf(num));
                edit.putInt("counterAngka", num);
                edit.apply();
            }
        });

//        buat ganti jadi 0 dan masukin ke prefered references
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = 0;
                counterNum.setText(String.valueOf(num));
                edit.putInt("counterAngka", num);
                edit.apply();
            }
        });

//        mengembalikkan angka yang sudah disimpan
        num = sp.getInt("counterAngka", 0);
        counterNum.setText(String.valueOf(num));

        return view;
    }
}