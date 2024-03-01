package com.example.appparking.ui.parking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentParkingBinding;
import com.example.appparking.newparking.CreateParking;
import com.example.appparking.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        String url = dataConfig.getEndPoint("/Parqueaderos/Obtener_P.php");

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Parking> listaParking = procesarRespuesta(response);
                adaptador.actualizarParking(listaParking);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error al obtener parqueaderos: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        queue.add(solicitud);
    }

    private List<Parking> procesarRespuesta(String response) {
        List<Parking> listaParking = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("registros");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);

                int idParqueadero = json.getInt("idparqueadero");
                String nombreParqueadero = json.getString("nombreparqueadero");
                String direccion = json.getString("direccion");
                double costoTarifaCarro = json.getDouble("costotarifacarro");
                double costoTarifaMoto = json.getDouble("costotarifamoto");
                double costoTarifaCamion = json.getDouble("costotarifacamion");
                double costoTarifaCamioneta = json.getDouble("costotarifacamioneta");

                // objeto Parking y agregarlo a la lista
                Parking parking = new Parking(
                        String.valueOf(idParqueadero),
                        nombreParqueadero,
                        direccion
                );
                listaParking.add(parking);
            }
        } catch (JSONException e) {
            e.printStackTrace();

        }

        return listaParking;
    }

    @Override
    public void onResume() {
        super.onResume();
        obtenerParqueaderos();
    }
}