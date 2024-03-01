package com.example.appparking.newparking;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateParking extends AppCompatActivity {

    MaterialButton btnAgregarParking;
    FloatingActionButton btnRegrasarVistanuevoParqueadero;
    TextInputLayout etqNombreParking, etqDireccion, etqTarifaCarro, etqTarifaMoto, etqTarifaCamioneta, etqTarifaCamion;
    Config dataConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_parking);

        dataConfig = new Config(getApplicationContext());
        initViews();
        eventsButton();
    }

    private void initViews() {
        etqNombreParking = findViewById(R.id.etqNombreParking);
        etqDireccion = findViewById(R.id.etqDireccion);
        etqTarifaCarro = findViewById(R.id.etqTarifaCarro);
        etqTarifaMoto = findViewById(R.id.etqTarifaMoto);
        etqTarifaCamioneta = findViewById(R.id.etqTarifaCamioneta);
        etqTarifaCamion = findViewById(R.id.etqTarifaCamion);
        btnAgregarParking = findViewById(R.id.btnAgregarParking);

        //tipo de entrada para aceptar solo números
        etqTarifaCarro.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        etqTarifaMoto.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        etqTarifaCamioneta.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        etqTarifaCamion.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
    }


    private void eventsButton() {
        btnRegrasarVistanuevoParqueadero = findViewById(R.id.btnRegrasarVistanuevoParqueadero);
        btnRegrasarVistanuevoParqueadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAgregarParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etqNombreParking.getEditText().getText().toString().isEmpty() || etqDireccion.getEditText().getText().toString().isEmpty() || etqTarifaCarro.getEditText().getText().toString().isEmpty() || etqTarifaMoto.getEditText().getText().toString().isEmpty() || etqTarifaCamioneta.getEditText().getText().toString().isEmpty() || etqTarifaCamion.getEditText().getText().toString().isEmpty()) {
                    Toast.makeText(CreateParking.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    agregarNuevoParqueadero();
                }
            }
        });
    }

    public void limpiarCampos() {
        etqNombreParking.getEditText().setText("");
        etqDireccion.getEditText().setText("");
        etqTarifaCarro.getEditText().setText("");
        etqTarifaMoto.getEditText().setText("");
        etqTarifaCamioneta.getEditText().setText("");
        etqTarifaCamion.getEditText().setText("");
    }

    private void agregarNuevoParqueadero() {
        String nombre = etqNombreParking.getEditText().getText().toString();
        String direccion = etqDireccion.getEditText().getText().toString();
        String tarifaCarro = etqTarifaCarro.getEditText().getText().toString();
        String tarifaMoto = etqTarifaMoto.getEditText().getText().toString();
        String tarifaCamioneta = etqTarifaCamioneta.getEditText().getText().toString();
        String tarifaCamion = etqTarifaCamion.getEditText().getText().toString();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/Parqueaderos/verificarP.php");


        StringRequest solicitud = new StringRequest(Request.Method.GET, url + "?nombreparqueadero=" + nombre + "&direccion=" + direccion,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String estado = jsonResponse.getString("estado");

                            if (estado.equals("existe")) {
                                Toast.makeText(CreateParking.this, "El parqueadero ya está registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                insertarParqueadero(nombre, direccion, tarifaCarro, tarifaMoto, tarifaCamioneta, tarifaCamion);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CreateParking.this, "Error  parqueadero: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateParking.this, "Error al verificar parqueadero: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        queue.add(solicitud);
    }

    private void insertarParqueadero(String nombre, String direccion, String tarifaCarro, String tarifaMoto, String tarifaCamioneta, String tarifaCamion) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/Parqueaderos/Insert_P.php");

        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                limpiarCampos();
                Toast.makeText(CreateParking.this, "Parqueadero agregado correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateParking.this, "Error al agregar parqueadero: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombreparqueadero", nombre);
                params.put("direccion", direccion);
                params.put("costotarifacarro", tarifaCarro);
                params.put("costotarifamoto", tarifaMoto);
                params.put("costotarifacamioneta", tarifaCamioneta);
                params.put("costotarifacamion", tarifaCamion);

                return params;
            }
        };

        queue.add(solicitud);
    }

}
