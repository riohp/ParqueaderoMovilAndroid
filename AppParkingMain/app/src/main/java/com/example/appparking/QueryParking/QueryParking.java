package com.example.appparking.QueryParking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.navbar.navbar_main;
import com.example.appparking.utils.Config;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryParking extends AppCompatActivity implements AdaptadorQueryParking.OnItemClickListener{

    RecyclerView rvParkingQuery;
    AdaptadorQueryParking adaptador;
    List<Parkings> listaParkings = new ArrayList<>();;

    Config dataConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_parking);

        rvParkingQuery = findViewById(R.id.rvParkingQuery);
        dataConfig = new Config(getApplicationContext());
        cargarParkings();

        // Configurar el adaptador con el Listener
        adaptador = new AdaptadorQueryParking(listaParkings);
        adaptador.setOnItemClickListener(this);
        rvParkingQuery.setAdapter(adaptador);
        rvParkingQuery.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void onItemClick(int position) {
        Parkings parking = listaParkings.get(position);
        System.out.println("El parking seleccionado es: "+parking.getIdparking());

        sharedPref(parking.getNombre(), parking.getDireccion(), parking.getIdparking());
    }

    public void cargarParkings(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = dataConfig.getEndPoint("/getAll/PerfilAPi/getInfoPerfil.php");

        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("El servidor POST responde con:"+response);
                    JSONObject respuesta = new JSONObject(response);
                    JSONArray lista = respuesta.getJSONArray("data");
                    System.out.println("La lista de parkings es: "+lista.length());
                    for (int i = 0; i < lista.length(); i++) {
                        JSONObject parking = lista.getJSONObject(i);
                        listaParkings.add(new Parkings(parking.getString("NombreParqueadero"), parking.getString("direccion"), parking.getString("idparqueadero")));
                    }
                    adaptador.notifyDataSetChanged();

                }catch (Exception e){
                    System.out.println("Error al cargar los parkings");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor POST responde con:");
                System.out.println(error.getMessage());
            }
        }){ protected Map<String, String> getParams(){
            Map<String, String> params = new HashMap<String, String>();
            String documento = getDocumentoSharedPreferences();
            params.put("documento", documento);
            return params;
            }
        };
        queue.add(solicitud);
    }

    public String getDocumentoSharedPreferences(){
        SharedPreferences archivo = getSharedPreferences("app_parking", Context.MODE_PRIVATE);
        return archivo.getString("documento", null);
    }

    public void sharedPref(String nombre, String direccion, String idparking){
        SharedPreferences archivo = getSharedPreferences( "datos_parking", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = archivo.edit();
        editor.putString("nombre_parking", nombre);
        editor.putString("direccion_parking", direccion);
        editor.putString("id_parking", idparking);

        editor.apply();

        Intent intencion = new Intent(getApplicationContext(), navbar_main.class);
        startActivity(intencion);
        finish();
    }


}