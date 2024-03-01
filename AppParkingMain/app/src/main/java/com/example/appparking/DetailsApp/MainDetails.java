package com.example.appparking.DetailsApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.appparking.MainActivity;
import com.example.appparking.R;
import com.example.appparking.authentication.Login;
import com.example.appparking.welcome.WelcomeActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainDetails extends AppCompatActivity {

    LinearLayout ininfoCreditsLayout, cerrarSesionLayout;

    FloatingActionButton btnBackreto;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details2);
        ininfoCreditsLayout = findViewById(R.id.infoCredits);
        cerrarSesionLayout = findViewById(R.id.cerrarSesion);
        btnBackreto = findViewById(R.id.btnBackreto);

        btnBackreto.setOnClickListener(v -> {
            System.out.println("Regresando a la actividad principal");
            finish();
        });

        ininfoCreditsLayout.setOnClickListener(v -> {
            //Intent intent = new Intent(MainDetails.this, InfoCredits.class);
            //startActivity(intent);
        });

        cerrarSesionLayout.setOnClickListener(v -> {
            // Limpiar preferencias compartidas
            SharedPreferences preferences = getSharedPreferences("app_parking", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            // Iniciar actividad de bienvenida
            Intent intent = new Intent(MainDetails.this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Evita que se vuelva a la actividad anterior al presionar el botón de retroceso
            startActivity(intent);
            finish(); // Finalizar actividad actual
        });

    }
}