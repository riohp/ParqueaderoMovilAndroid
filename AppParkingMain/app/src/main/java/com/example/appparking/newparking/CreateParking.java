package com.example.appparking.newparking;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.appparking.R;
import com.example.appparking.utils.Config;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateParking extends AppCompatActivity {


    FloatingActionButton btnRegrasarVistanuevoParqueadero;

    Config dataConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_parking);


        dataConfig = new Config(getApplicationContext());
        eventInbuttonViewback();
    }

    public void eventInbuttonViewback(){
        FloatingActionButton btnRegrasarVistanuevoParqueadero = findViewById(R.id.btnRegrasarVistanuevoParqueadero);
        btnRegrasarVistanuevoParqueadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}