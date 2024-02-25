package com.example.appparking.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appparking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.search.SearchBar;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {


    EditText campoBusqueda;
    FloatingActionButton btnBuscar;

    RecyclerView recyclerUsarios;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        campoBusqueda = root.findViewById(R.id.campo_busqueda);
        btnBuscar = root.findViewById(R.id.btn_buscar);
        recyclerUsarios = root.findViewById(R.id.recyclerUsarios);

        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("288747744", "carlos", "carlos@gmail.com", "ACTIVO", "ADMINISTRADOR"));

        recyclerUsarios = root.findViewById(R.id.recyclerUsarios);
        recyclerUsarios.setLayoutManager(new LinearLayoutManager((getActivity().getApplicationContext())));
        AdaptadorDeItemsUsuarios adaptador = new AdaptadorDeItemsUsuarios(listaUsuarios);
        recyclerUsarios.setAdapter(adaptador);

        return root;


    }

}