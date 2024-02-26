package com.example.appparking.navbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.example.appparking.R;

import com.example.appparking.databinding.ActivityNavbarMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class navbar_main extends AppCompatActivity {

    private ActivityNavbarMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavbarMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view_2);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_navbar_main);
        NavigationUI.setupWithNavController(binding.navView2, navController);
    }
}