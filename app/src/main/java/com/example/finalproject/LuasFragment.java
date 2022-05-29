package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class LuasFragment extends Fragment {

    public LuasFragment() {
        // Required empty public constructor
    }

    Double sisiHitung = 0.0, alasHitung = 0.0, tinggiHitung = 0.0, radiusHitung = 0.0, resultHitung = 0.0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_luas, container, false);
        EditText sisi = (EditText) view.findViewById(R.id.sisi_et);
        EditText alas = (EditText) view.findViewById(R.id.alas_et);
        EditText tinggi = (EditText) view.findViewById(R.id.tinggi_et);
        EditText radius = (EditText) view.findViewById(R.id.radius_et);
        TextView result = (TextView) view.findViewById(R.id.result_text);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_area);


        List<String> shapes = new ArrayList<String>();
        shapes.add("Pilih bentuk");
        shapes.add("Persegi");
        shapes.add("Segitiga");
        shapes.add("Lingkaran");

        // buat spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, shapes);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choiceItem = parent.getItemAtPosition(position).toString();

                sisiHitung = Double.parseDouble(sisi.getText().toString());
                alasHitung = Double.parseDouble(alas.getText().toString());
                tinggiHitung = Double.parseDouble(tinggi.getText().toString());
                radiusHitung = Double.parseDouble(radius.getText().toString());

                if(choiceItem.equals("Pilih bentuk")){
                    Toast.makeText(getActivity(), "Pilih bentuk yang diinginkan!", Toast.LENGTH_SHORT).show();
                }else if(choiceItem.equals("Persegi")){
                    resultHitung = sisiHitung * sisiHitung;

                    result.setText("Luas Persegi: " + resultHitung);
                }else if(choiceItem.equals("Segitiga")){
                    resultHitung = (alasHitung * tinggiHitung)/2;

                    result.setText("Luas Segitiga: " + resultHitung);
                }else if(choiceItem.equals("Lingkaran")){
                    resultHitung = 3.14 * radiusHitung * radiusHitung;

                    result.setText("Luas Lingkaran: " + resultHitung);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
}