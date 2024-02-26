package com.example.appparking.navbar.ui.search_vehicle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.appparking.R;
import com.example.appparking.databinding.FragmentSearchVehicleBinding;

import java.util.ArrayList;
import java.util.List;


public class SearchVehicleFragment extends Fragment {

    EditText campo_busqueda_vehicle;
    RecyclerView recyclerVehiculos;
    private FragmentSearchVehicleBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search_vehicle, container, false);

        recyclerVehiculos = root.findViewById(R.id.recyclerVehiculos);

       List<Vehiculo> listaVehiculos = new ArrayList<>();
       listaVehiculos.add(new Vehiculo("ABC123", "Moto", "Yamaha", "2021"));
       listaVehiculos.add(new Vehiculo("ABC123", "Carro", "Chevrolet", "2021"));
       listaVehiculos.add(new Vehiculo("ABC123", "Carro", "Chevrolet", "2021"));

       recyclerVehiculos = root.findViewById(R.id.recyclerVehiculos);
       recyclerVehiculos.setLayoutManager(new LinearLayoutManager((getActivity().getApplicationContext())));
       Adaptadordevehiculos adaptador = new Adaptadordevehiculos(listaVehiculos);
       recyclerVehiculos.setAdapter(adaptador);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}