package com.example.appparking.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.DetailsApp.MainDetails;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentHomeBinding;
import com.example.appparking.utils.Config;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    Config dataConfig;
    TextView etqIdTicket;
    TextView etqFomaterHorario;
    TextView etqEstadoTicket;
    TextView etqNumTicket;
    TextView etqPlaca;
    TextView etqTipoVehiculo;
    TextView etqFechaIngreso;
    TextView etqName;
    TextView etqGnanciasAprox;
    TextView etqGnanciasPorc;

    FloatingActionButton btnAllopcions;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dataConfig = new Config(requireContext());
        etqIdTicket = root.findViewById(R.id.etqIdTicketHora);
        etqFomaterHorario = root.findViewById(R.id.etqFomaterHorario);
        etqEstadoTicket = root.findViewById(R.id.etqEstadoTicket);
        etqNumTicket = root.findViewById(R.id.etqNumTicket);
        etqPlaca = root.findViewById(R.id.etqPlaca);
        etqTipoVehiculo = root.findViewById(R.id.etqTipoVehiculo);
        etqFechaIngreso = root.findViewById(R.id.etqFechaIngreso);
        etqName = root.findViewById(R.id.etqName);
        etqGnanciasAprox = root.findViewById(R.id.etqGnanciasAprox);
        etqGnanciasPorc = root.findViewById(R.id.etqGnanciasPorc);
        btnAllopcions = root.findViewById(R.id.btnAllopcions);

        btnAllopcions.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MainDetails.class);
            startActivity(intent);
        });

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("app_parking", Context.MODE_PRIVATE);
        String documento = sharedPreferences.getString("documento", "");
        String nombre = sharedPreferences.getString("nombre", "");

        etqName.setText(nombre);

        cambiarContenido(documento);
        cambiarContenido2();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void cambiarContenido(String documento) {
        queryInfoTicket(documento);
    }

    public void queryInfoTicket(String documento) {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String url = dataConfig.getEndPoint("/Tikets/Obtener_T.php");

        StringRequest solicitud =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("El servidor GET responde con:");
                System.out.println(response);
                try {
                    JSONObject datos = new JSONObject(response);
                    boolean status = datos.getBoolean("status");
                    if (status) {
                        JSONObject data = datos.getJSONObject("data");
                        // Obtener el dato de entrada
                        String horaEntrada = data.getString("horaentrada");
                        // Obtener el estado
                        String estado = data.getString("estado");
                        // Obtener el idvehiculo
                        String idVehiculo = data.getString("idvehiculo");
                        // obtener el id del ticket
                        String idTicket = data.getString("idtickets");

                        extraerHora(horaEntrada);
                        extraerEstado(estado);
                        extraerIdVehiculo(idVehiculo);
                        extraerFecha(horaEntrada);

                        etqNumTicket.setText(idTicket);

                    } else {
                        String errorMessage = datos.getString("message");
                        System.out.println("Error: " + errorMessage);
                    }
                } catch (JSONException e) {
                    // Manejar la excepción en caso de un error al procesar el JSON
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor GET responde con un error:");
                System.out.println(error.getMessage());
            }

        });

        queue.add(solicitud);
    }

    public void extraerHora(String horaEntradaCompleta) {
        String horaEntrada = horaEntradaCompleta.substring(11, 16);

        System.out.println("ESRTA ES LA HORA QUE CAPTURE");
        System.out.println(horaEntrada);

        etqIdTicket.setText(horaEntrada);

        int horas = Integer.parseInt(horaEntrada.substring(0, 2));

        boolean esPM = horas >= 12;

        String formato = (esPM)? " PM" : " AM";
        etqFomaterHorario.setText(formato);
    }

    public void extraerEstado(String estado) {
        if (estado.equals("activo")) {
            etqEstadoTicket.setText("No a salido");
        } else {
            etqEstadoTicket.setText("Si a salido");
        }
    }

    public void extraerIdVehiculo(String idVehiculo) {
        System.out.println("El id del vehiculo es: " + idVehiculo);
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String url = dataConfig.getEndPoint("/Vehiculos/Obtener_V.php");

        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("El servidor PARA VEHICULO responde con:");
                System.out.println(response);
                try {
                    JSONObject datos = new JSONObject(response);
                    boolean status = datos.getBoolean("status");
                    if (status) {
                        JSONObject data = datos.getJSONObject("data");
                        String placa = data.getString("placa");
                        String tipovehiculo = data.getString("tipovehiculo");
                        etqPlaca.setText(placa);
                        etqTipoVehiculo.setText(tipovehiculo);
                    } else {
                        String errorMessage = datos.getString("message");
                        Toast.makeText(requireContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(requireContext(), "Error: Respuesta inválida del servidor", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(requireContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("idvehiculo", idVehiculo);


                return params;
            }
        };

        queue.add(solicitud);
    }

    public void extraerFecha(String horaEntradaCompleta) {
        String fecha = horaEntradaCompleta.substring(0, 10);
        etqFechaIngreso.setText(fecha);
    }

    public void cambiarContenido2(){
        int montoAproxoximado = 150000;

        etqGnanciasAprox.setText(String.valueOf(montoAproxoximado));

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String url = dataConfig.getEndPoint("/Tikets/Obtener_P.php");

        StringRequest solicitud =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("El servidor GET TTOSL responde con:");
                System.out.println(response);
                try {
                    JSONObject datos = new JSONObject(response);

                    boolean status = datos.getBoolean("status");
                    if (status) {


                        String costo_recaudado = datos.getString("total_costo");

                        double total_costo_double = Double.parseDouble(costo_recaudado);
                        double diferencia = montoAproxoximado - total_costo_double;
                        double porcentaje;

                        if (diferencia > 0) {
                            porcentaje = (diferencia / montoAproxoximado) * 100;
                            System.out.println("Porcentaje de pérdida: " + porcentaje + "%");
                            String numeroRedondeado = String.format("%.2f", porcentaje);
                            etqGnanciasPorc.setText("-"+numeroRedondeado+"%");

                        } else if (diferencia < 0) {
                            diferencia = Math.abs(diferencia);
                            porcentaje = (diferencia / montoAproxoximado) * 100;
                            System.out.println("Porcentaje de ganancia adicional: " + porcentaje + "%");
                            String numeroRedondeado = String.format("%.2f", porcentaje);
                            etqGnanciasPorc.setText("+"+numeroRedondeado+"%");
                        } else {
                           etqGnanciasPorc.setText("0%");
                        }




                    } else {
                        String errorMessage = datos.getString("message");
                        System.out.println("Error: " + errorMessage);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor GET responde con un error:");
                System.out.println(error.getMessage());
            }

        });

        queue.add(solicitud);




    }

}