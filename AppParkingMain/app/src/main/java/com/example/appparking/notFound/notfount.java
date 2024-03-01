package com.example.appparking.notFound;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.appparking.R;
import com.google.android.material.button.MaterialButton;

public class notfount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfount);

        MaterialButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}