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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentSearchVehicleBinding;
import com.example.appparking.utils.Config;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SearchVehicleFragment extends Fragment {

    EditText campo_busqueda_vehicle;
    RecyclerView recyclerVehiculos;

    Adaptadordevehiculos adaptador;
    private FragmentSearchVehicleBinding binding;

    Config dataConfig;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search_vehicle, container, false);

        recyclerVehiculos = root.findViewById(R.id.recyclerVehiculos);
        dataConfig = new Config(requireActivity().getApplicationContext());

       //List<Vehiculo> listaVehiculos = new ArrayList<>();
       //listaVehiculos.add(new Vehiculo("ABC123", "moto", "Yamaha", "2021"));
       //listaVehiculos.add(new Vehiculo("ABC123", "carro", "Chevrolet", "2021"));
       //listaVehiculos.add(new Vehiculo("ABC123", "carro", "Chevrolet", "2021"));

       recyclerVehiculos = root.findViewById(R.id.recyclerVehiculos);
       recyclerVehiculos.setLayoutManager(new LinearLayoutManager((getActivity().getApplicationContext())));

        obtenerVehiculos();

        return root;
    }

    private void obtenerVehiculos(){
        String url = dataConfig.getEndPoint("/getAll/obtenerAll.php?idpark=1");

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Vehiculo> listaVehiculos = procesarRespuesta(response);
                adaptador = new Adaptadordevehiculos(listaVehiculos);
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


    public List<Vehiculo> procesarRespuesta(String response) {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        try {
            JSONObject respuesta = new JSONObject(response);
            JSONArray lista = respuesta.getJSONArray("registros");
            for (int i = 0; i<lista.length(); i++){

                Vehiculo vehiculo = new Vehiculo(
                        lista.getJSONObject(i).getString("placa"),
                        lista.getJSONObject(i).getString("tipovehiculo"),
                        lista.getJSONObject(i).getString("marca"),
                        lista.getJSONObject(i).getString("estado")
                );
                listaVehiculos.add(vehiculo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaVehiculos;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}