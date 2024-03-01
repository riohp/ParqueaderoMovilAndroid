package com.example.appparking.navbar.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.appparking.DetailsApp.MainDetails;
import com.example.appparking.QueryParking.QueryParking;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentCasitaBinding;
import com.example.appparking.notFound.notfount;
import com.example.appparking.utils.Config;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CasitaFragment extends Fragment {

    Config dataConfig;

    TextView etqName;
    TextView etqNombreParking;
    TextView etqDirrecionparking;

    FloatingActionButton btnOptions;

    MaterialButton btnCambiarParking, btnVehiculos;
    private FragmentCasitaBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCasitaBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        dataConfig = new Config(requireContext());
        etqName = root.findViewById(R.id.etqName);
        etqNombreParking = root.findViewById(R.id.etqNombreParking);
        etqDirrecionparking = root.findViewById(R.id.etqDirrecionparking);
        btnCambiarParking = root.findViewById(R.id.btnCambiarParking);
        btnVehiculos = root.findViewById(R.id.btnVehiculos);
        btnOptions = root.findViewById(R.id.btnOptions);

        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MainDetails.class);
                startActivity(intent);
            }
        });

        btnVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), notfount.class);
                startActivity(intent);
            }
        });

        SharedPreferences preferences = requireContext().getSharedPreferences("datos_parking", Context.MODE_PRIVATE);
        String nombreParking = preferences.getString("nombre_parking", "No existe la informacion");
        String direccion_parking = preferences.getString("direccion_parking", "No existe la informacion");

        etqNombreParking.setText(nombreParking);
        etqDirrecionparking.setText(direccion_parking);

        SharedPreferences preferencesUser = requireContext().getSharedPreferences("app_parking", Context.MODE_PRIVATE);
        String nombre = preferencesUser.getString("nombre", "No existe la informacion");
        etqName.setText(nombre);

        btnCambiarParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Cambiando de parking");
                Intent intent = new Intent(requireContext(), QueryParking.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}