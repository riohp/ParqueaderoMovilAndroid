package com.example.appparking.welcome;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.appparking.R;
import com.example.appparking.authentication.Login;
import com.example.appparking.utils.Config;
import com.example.appparking.vehiclequery.VehicleQueryActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button btnViewCar, btnLogin;

    Config dataConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogin = findViewById(R.id.btnLogin);
        btnViewCar = findViewById(R.id.btnViewCar);

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, Login.class));
        });

        btnViewCar.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, VehicleQueryActivity.class));
        });

        dataConfig = new Config(getApplicationContext());
    }
}