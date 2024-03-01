package com.example.appparking.navbar.ui.search_vehicle;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.material.button.MaterialButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class SearchVehicleFragment extends Fragment implements Adaptadordevehiculos.OnItemClickListener {

    EditText campo_busqueda_vehicle;
    RecyclerView recyclerVehiculos;
    List<Vehiculo> listaVehiculos = new ArrayList<>();
    Adaptadordevehiculos adaptador;

    private FragmentSearchVehicleBinding binding;

    Config dataConfig;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search_vehicle, container, false);

        recyclerVehiculos = root.findViewById(R.id.recyclerVehiculos);
        dataConfig = new Config(requireActivity().getApplicationContext());

       recyclerVehiculos = root.findViewById(R.id.recyclerVehiculos);
       recyclerVehiculos.setLayoutManager(new LinearLayoutManager((getActivity().getApplicationContext())));

        obtenerVehiculos();

        return root;
    }

    public void onItemClick(int position) {
        Vehiculo vehicle = listaVehiculos.get(position);
        System.out.println("El placa seleccionado es: "+vehicle.getPlaca());
        saberMasPorPlaca(vehicle.getPlaca());

    }

    private void obtenerVehiculos(){
        String id_paring = id_parking();

        String url = dataConfig.getEndPoint("/getAll/obtenerAll.php?idpark="+id_paring);

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listaVehiculos = procesarRespuesta(response);
                adaptador = new Adaptadordevehiculos(listaVehiculos);
                adaptador.setOnItemClickListener(SearchVehicleFragment.this);
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

    public void saberMasPorPlaca(String placa){
        String url = dataConfig.getEndPoint("/getAll/obtenerPorPlaca.php?placa="+placa);
        final double[] costoVehiculo = {0};
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("La respuesta es: "+response);
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray lista = obj.getJSONArray("registros");
                    JSONObject registro = lista.getJSONObject(0);
                    String placa = registro.getString("placa");
                    String HoraEntrada = registro.getString("horaentrada");
                    String tipoVehiculo = registro.getString("tipovehiculo");

                    //algoritmo para calcular el precio
                    if (tipoVehiculo.equals("carro")){
                        costoVehiculo[0] = 2000;
                        System.out.println("El precio es: 2000");
                    }else if (tipoVehiculo.equals("moto")){
                        costoVehiculo[0] = 600;
                        System.out.println("El precio es: 600");
                    }else{
                        costoVehiculo[0] = 4145;
                        System.out.println("El precio es: 0");
                    }

                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date fechaIngreso = formato.parse(HoraEntrada);
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm", Locale.getDefault());
                    String fecha = formatoFecha.format(fechaIngreso);
                    String hora = formatoHora.format(fechaIngreso);
                    double costoTemporal = calcularCosto(fechaIngreso, costoVehiculo[0]);

                    System.out.println("El costo es: "+costoTemporal);

                    showDialog(placa, fecha, hora, costoTemporal);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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

    public double calcularCosto(Date horaIngreso, double costoPorHora) {
        Calendar calendarActual = Calendar.getInstance();
        Date horaActual = calendarActual.getTime();

        long diferenciaMs = horaActual.getTime() - horaIngreso.getTime();

        long horas = diferenciaMs / (60 * 60 * 1000);

        double costoTotal = horas * costoPorHora;

        return costoTotal;
    }

    public void showDialog(String placa, String horaEntrada, String FechaEntrada, double costo){
        final Dialog dialog = new Dialog(requireActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sheet_model);

        TextView placaVehiculo = dialog.findViewById(R.id.txtPlaca);
        TextView horaEntradaVehiculo = dialog.findViewById(R.id.txtFechaIngreso);
        TextView fechaEntradaVehiculo = dialog.findViewById(R.id.txtHoraIngreso);
        TextView costoVehiculo = dialog.findViewById(R.id.txtValorPagar);

        MaterialButton btnCerrar = dialog.findViewById(R.id.btnFinalizar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        placaVehiculo.setText(placa);
        horaEntradaVehiculo.setText(horaEntrada);
        fechaEntradaVehiculo.setText(FechaEntrada);
        costoVehiculo.setText(String.valueOf(costo));

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}