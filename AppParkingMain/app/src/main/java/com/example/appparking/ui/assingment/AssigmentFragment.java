package com.example.appparking.ui.assingment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentAssigmentBinding;
import com.example.appparking.utils.Config;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssigmentFragment extends Fragment {

    private FragmentAssigmentBinding binding;

    private AutoCompleteTextView autoCompleteTextViewVendedor;
    private AutoCompleteTextView autoCompleteTextViewParqueadero;
    private Button btnAsignar;

    private List<String> vendedoresList;
    private List<String> parqueaderosList;

    // Declarar las listas para los IDs de vendedores y parqueaderos
    private List<String> idVendedoresList;
    private List<String> idParqueaderosList;

    Config dataConfig;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAssigmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dataConfig = new Config(requireContext());

        autoCompleteTextViewVendedor = root.findViewById(R.id.etpVendedor);
        autoCompleteTextViewParqueadero = root.findViewById(R.id.etpParqueadero);
        btnAsignar = root.findViewById(R.id.btnAsignar);

        vendedoresList = new ArrayList<>();
        parqueaderosList = new ArrayList<>();
        idVendedoresList = new ArrayList<>();
        idParqueaderosList = new ArrayList<>();


        obtenerVendedoresYParqueaderos();


        btnAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el vendedor y parqueadero seleccionados
                String vendedorSeleccionado = autoCompleteTextViewVendedor.getText().toString();
                String parqueaderoSeleccionado = autoCompleteTextViewParqueadero.getText().toString();


                if (!vendedorSeleccionado.isEmpty() && !parqueaderoSeleccionado.isEmpty()) {

                    asignarVendedorAParqueadero(vendedorSeleccionado, parqueaderoSeleccionado);
                } else {

                    Toast.makeText(requireContext(), "Por favor, selecciona un vendedor y un parqueadero", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return root;
    }

    private void obtenerVendedoresYParqueaderos() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        String url = dataConfig.getEndPoint("/Usuarios/Obtener_vp.php");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray vendedores = response.getJSONArray("registros");
                            JSONArray parqueaderos = response.getJSONArray("parkings");


                            for (int i = 0; i < vendedores.length(); i++) {
                                JSONObject vendedorObject = vendedores.getJSONObject(i);
                                String idVendedor = vendedorObject.getString("idusuario");
                                String vendedor = vendedorObject.getString("nombreusuario");

                                // Almacenar el ID y el nombre del vendedor
                                vendedoresList.add(vendedor);
                                idVendedoresList.add(idVendedor);
                            }


                            for (int i = 0; i < parqueaderos.length(); i++) {
                                JSONObject parqueaderoObject = parqueaderos.getJSONObject(i);
                                String idParqueadero = parqueaderoObject.getString("idparqueadero");
                                String parqueadero = parqueaderoObject.getString("nombreparqueadero");

                                // Almacenar el ID y el nombre del parqueadero
                                parqueaderosList.add(parqueadero);
                                idParqueaderosList.add(idParqueadero); // Agrega esta lista también
                            }

                            // Poblar los selectores con los datos obtenidos
                            ArrayAdapter<String> vendedorAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, vendedoresList);
                            autoCompleteTextViewVendedor.setAdapter(vendedorAdapter);

                            ArrayAdapter<String> parqueaderoAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, parqueaderosList);
                            autoCompleteTextViewParqueadero.setAdapter(parqueaderoAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }


    private void asignarVendedorAParqueadero(String vendedor, String parqueadero) {

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String url = dataConfig.getEndPoint("/Usuarios/asigno.php");
        System.out.println("URL asigno: " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.has("error")) {
                                String errorMessage = jsonObject.getString("error");
                                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            } else if (jsonObject.has("mensaje")) {
                                String successMessage = jsonObject.getString("mensaje");
                                Toast.makeText(requireContext(), successMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud si es necesario
                        Toast.makeText(requireContext(), "Error al asignar vendedor a parqueadero", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Crear un mapa con los datos que se enviarán al servidor
                Map<String, String> params = new HashMap<>();
                params.put("idusuario", idVendedoresList.get(vendedoresList.indexOf(vendedor)));
                params.put("idparqueadero", idParqueaderosList.get(parqueaderosList.indexOf(parqueadero)));
                return params;
            }
        };

        queue.add(stringRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
