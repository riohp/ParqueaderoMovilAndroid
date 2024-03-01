package com.example.appparking.navbar.ui.search_vehicle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentSearchVehicleBinding;
import com.example.appparking.utils.Config;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class SearchVehicleFragment extends Fragment {

    TextInputEditText campo_busqueda_vehicle;
    RecyclerView recyclerVehiculos;

    Adaptadordevehiculos adaptador;
    private FragmentSearchVehicleBinding binding;
    List<Vehiculo> listaCompletaVehiculos;
    Config dataConfig;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search_vehicle, container, false);
        campo_busqueda_vehicle = root.findViewById(R.id.campo_busqueda_vehicle);
        recyclerVehiculos = root.findViewById(R.id.recyclerVehiculos);
        dataConfig = new Config(requireActivity().getApplicationContext());

       recyclerVehiculos = root.findViewById(R.id.recyclerVehiculos);
       recyclerVehiculos.setLayoutManager(new LinearLayoutManager((getActivity().getApplicationContext())));

        obtenerVehiculos();

        return root;
    }

    private void obtenerVehiculos(){
        String id_paring = id_parking();

        String url = dataConfig.getEndPoint("/getAll/obtenerAll.php?idpark="+id_paring);

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listaCompletaVehiculos = procesarRespuesta(response);
                adaptador = new Adaptadordevehiculos(listaCompletaVehiculos);
                recyclerVehiculos.setAdapter(adaptador);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        queue.add(solicitud);
    }

    private String id_parking() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("datos_parking", Context.MODE_PRIVATE);
        return preferences.getString("id_parking", "0");
    }


    public List<Vehiculo> procesarRespuesta(String response) {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        try {
            JSONObject respuesta = new JSONObject(response);
            JSONArray lista = respuesta.getJSONArray("registros");
            for (int i = 0; i<lista.length(); i++){
                String marca = lista.getJSONObject(i).getString("marca");
                String modelo = lista.getJSONObject(i).getString("modelo");
                String marcamodelo = marca+" "+modelo;
                String hora = horas(lista.getJSONObject(i).getString("horaentrada"));

                Vehiculo vehiculo = new Vehiculo(
                        lista.getJSONObject(i).getString("placa"),
                        lista.getJSONObject(i).getString("tipovehiculo"),
                        marcamodelo,
                        hora
                );
                listaVehiculos.add(vehiculo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaVehiculos;
    }


    public String horas(String fecha){
        String result = "N/A";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime horaemtrada = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            LocalDateTime horaActual = LocalDateTime.now();

            long diferenciaHoras = ChronoUnit.HOURS.between(horaemtrada, horaActual);

            result = "Hace " + diferenciaHoras + " horas";
        }
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        campo_busqueda_vehicle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textoBusqueda = charSequence.toString().toLowerCase().trim();
                if (adaptador != null) {
                    List<Vehiculo> vehiculosFiltrados = filtrarVehiculos(textoBusqueda);
                    adaptador.actualizarVehiculos(vehiculosFiltrados);
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private List<Vehiculo> filtrarVehiculos(String textoBusqueda) {
        List<Vehiculo> vehiculosFiltrados = new ArrayList<>();
        if(listaCompletaVehiculos != null){
            for (Vehiculo vehiculo : listaCompletaVehiculos){
                if (vehiculo.getPlaca().toLowerCase().contains(textoBusqueda)){
                    vehiculosFiltrados.add(vehiculo);
                }
            }
        }
        return vehiculosFiltrados;
    }

    @Override
    public void onResume() {
        super.onResume();
        obtenerVehiculos();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}