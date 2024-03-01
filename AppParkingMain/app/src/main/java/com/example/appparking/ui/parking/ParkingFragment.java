package com.example.appparking.ui.parking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentParkingBinding;
import com.example.appparking.newparking.CreateParking;
import com.example.appparking.utils.Config;

import java.util.ArrayList;
import java.util.List;


public class ParkingFragment extends Fragment {

    RecyclerView recyclerParking;

    private FragmentParkingBinding binding;

    TextView NewParking;

    AdaptadorItemsParking adaptador;

    Config dataConfig;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentParkingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerParking = root.findViewById(R.id.recyclerParking);
        recyclerParking.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptador = new AdaptadorItemsParking(new ArrayList<>());
        recyclerParking.setAdapter(adaptador);
        NewParking = root.findViewById(R.id.NewParking);
        dataConfig = new Config(requireActivity().getApplicationContext());

        NewParking.setOnClickListener(v -> {
            System.out.println("SE DIO CLICK EN CREAR NUEVO PARKING ");
            Intent intent = new Intent(getActivity(), CreateParking.class);
            startActivity(intent);
        });

        obtenerParqueaderos();

        return root;
    }

    public void obtenerParqueaderos() {
        List<Parking> listaParking = new ArrayList<>();

        Parking parking = new Parking("10 Plazas", "Parqueadero 1", "Calle 1 # 1-1");
        listaParking.add(parking);

        adaptador.actualizarParking(listaParking);

    }
}