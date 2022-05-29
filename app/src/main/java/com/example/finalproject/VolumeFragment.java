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


public class VolumeFragment extends Fragment {

    public VolumeFragment() {
        // Required empty public constructor
    }

    Double panjangHitung = 0.0, lebarHitung = 0.0, tinggiHitung = 0.0, radiusHitung = 0.0, resultHitung = 0.0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        EditText panjang = (EditText) view.findViewById(R.id.panjangV_et);
        EditText lebar = (EditText) view.findViewById(R.id.lebarV_et);
        EditText tinggi = (EditText) view.findViewById(R.id.tinggiV_et);
        EditText radius = (EditText) view.findViewById(R.id.radiusV_et);
        TextView result = (TextView) view.findViewById(R.id.resultV_text);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerV_area);


        List<String> shapes = new ArrayList<String>();
        shapes.add("Pilih bentuk");
        shapes.add("Balok");
        shapes.add("Pyramid");
        shapes.add("Tabung");

//        buat spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, shapes);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choiceItem = parent.getItemAtPosition(position).toString();

                panjangHitung = Double.parseDouble(panjang.getText().toString());
                lebarHitung = Double.parseDouble(lebar.getText().toString());
                tinggiHitung = Double.parseDouble(tinggi.getText().toString());
                radiusHitung = Double.parseDouble(radius.getText().toString());

                if(choiceItem.equals("Pilih bentuk")){
                    Toast.makeText(getActivity(), "Pilih bentuk yang diinginkan!", Toast.LENGTH_SHORT).show();
                }else if(choiceItem.equals("Balok")){
                    resultHitung = panjangHitung * lebarHitung * tinggiHitung;

                    result.setText("Volume Balok: " + resultHitung);
                }else if(choiceItem.equals("Pyramid")){
                    resultHitung = (panjangHitung * panjangHitung * tinggiHitung) / 3;

                    result.setText("Volume Pyramid: " + resultHitung);
                }else if(choiceItem.equals("Tabung")){
                    resultHitung = 3.14 * radiusHitung * radiusHitung * tinggiHitung;

                    result.setText("Volume Tabung: " + resultHitung);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
}