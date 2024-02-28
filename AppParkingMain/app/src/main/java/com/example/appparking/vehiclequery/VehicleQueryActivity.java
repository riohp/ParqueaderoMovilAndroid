package com.example.appparking.vehiclequery;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.utils.Config;
import com.google.android.material.button.MaterialButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VehicleQueryActivity extends AppCompatActivity {

    Button btnViewCar;
    EditText etpPlaca;

    TextView txtFieldError;

    View viweColorError;

    Config dataConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_query);
        btnViewCar = findViewById(R.id.btnViewCar);
        etpPlaca = findViewById(R.id.etpPlaca);
        txtFieldError = findViewById(R.id.txtFieldError);
        viweColorError = findViewById(R.id.viweColorError);
        dataConfig = new Config(getApplicationContext());
        txtFieldError.setText("");
        Drawable drawable_error = getResources().getDrawable(R.drawable.bottom_corner_border_error);
        Drawable drawable = getResources().getDrawable(R.drawable.bottom_corner_border);

        btnViewCar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtFieldError.setText("");
                btnViewCar.setEnabled(false);
                String placa = etpPlaca.getText().toString();
                System.out.println("placa "+placa);
                if (etpPlaca.getText().toString().isEmpty()) {
                    viweColorError.setBackground(drawable_error);
                    //etpPlaca.setError("Por favor, rellene el campo");
                    txtFieldError.setText("Por favor, Ingrese la placa del vehículo");
                    //Toast.makeText(VehicleQueryActivity.this, "Por favor, rellene el campo", Toast.LENGTH_SHORT).show();
                    btnViewCar.setEnabled(true);
                } else {
                    viweColorError.setBackground(drawable);
                    consultarVehiculo(placa);
                }
            }
        });
    }

    public void consultarVehiculo(String placa){
        // Construir la URL base
        String baseUrl = dataConfig.getEndPoint("/getAll/validarVehiculo.php");

        Uri.Builder builder = Uri.parse(baseUrl).buildUpon();
        builder.appendQueryParameter("placa", placa);

        String url = builder.build().toString();

        StringRequest stringResRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String message = jsonResponse.getString("message");
                    String status = jsonResponse.getString("status");
                    if (jsonResponse.has("data") && status.equalsIgnoreCase("true")) {
                        JSONObject dataObject = jsonResponse.getJSONObject("data");

                        if (dataObject.has("estado_ticket")) {
                            String estadoTicket = dataObject.getString("estado_ticket");

                            if (estadoTicket.equals("activo")) {
                                consultarEstadoVehiculo(placa);
                            } else {
                                txtFieldError.setText("El vehículo no esta en el parqueadero");
                                //Toast.makeText(VehicleQueryActivity.this, "El vehículo no esta, ASUSTESE MANO LO ROBARON", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            System.out.println("No se encontró el campo 'estado_ticket' en el objeto 'data'");
                        }
                    } else {
                        System.out.println("No se encontró el campo 'data' en la respuesta");
                        txtFieldError.setText(message);
                        //Toast.makeText(VehicleQueryActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                    btnViewCar.setEnabled(true);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar errores aquí
                btnViewCar.setEnabled(true);
                System.out.println("Error "+error);
            }
        });

        // Agregar la solicitud a la cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringResRequest);
    }


    private void showDialog(String placa, String fechaEntradaFormateada, String horaEntradaFormateada,  double costoTemporal, String tiempoTranscurrido) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        TextView txtPlaca = dialog.findViewById(R.id.txtPlaca);
        TextView txtFechaIngreso = dialog.findViewById(R.id.txtFechaIngreso);
        TextView txtHoraIngreso = dialog.findViewById(R.id.txtHoraIngreso);
        TextView txtValorPagar = dialog.findViewById(R.id.txtValorPagar);
        TextView txtTiempo = dialog.findViewById(R.id.txtTiempo);

        MaterialButton btnFinalizar = dialog.findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        txtPlaca.setText(placa);
        txtFechaIngreso.setText(fechaEntradaFormateada);
        txtHoraIngreso.setText(horaEntradaFormateada);
        txtValorPagar.setText(String.valueOf(costoTemporal));
        txtTiempo.setText(tiempoTranscurrido);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        btnViewCar.setEnabled(true);
    }


    public void consultarEstadoVehiculo(String placa){
        String baseUrl = dataConfig.getEndPoint("/getAll/obtenerPorPlaca.php");

        Uri.Builder builder = Uri.parse(baseUrl).buildUpon();
        builder.appendQueryParameter("placa", placa);

        String url = builder.build().toString();

        StringRequest stringResRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray registrosArray = jsonResponse.getJSONArray("registros");


                    for (int i = 0; i < registrosArray.length(); i++) {
                        JSONObject obj = new JSONObject(response);
                        JSONArray registros = obj.getJSONArray("registros");
                        JSONObject registro = registros.getJSONObject(0);


                        String placa = registro.getString("placa");
                        String tipoVehiculo = registro.getString("tipovehiculo");
                        String horaEntradaString = registro.getString("horaentrada");
                        String estado = registro.getString("estado");
                        String horaSalidaString = registro.getString("horasalida");
                        String costoTotal = registro.getString("costototal");


                        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        Date fechaEntrada = formatoEntrada.parse(horaEntradaString);
                        SimpleDateFormat formatoFechaEntrada = new SimpleDateFormat("dd, MMMM yyyy", Locale.getDefault());
                        String fechaEntradaFormateada = formatoFechaEntrada.format(fechaEntrada);
                        String tiempoTranscurrido = calcularTiempoTranscurrido(fechaEntrada);
                        double costoTemporal = calcularCosto(fechaEntrada, 600);

                        SimpleDateFormat formatoHoraEntrada = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        String horaEntradaFormateada = formatoHoraEntrada.format(fechaEntrada);


                        showDialog(placa, fechaEntradaFormateada, horaEntradaFormateada, costoTemporal, tiempoTranscurrido);

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar errores aquí
                btnViewCar.setEnabled(true);
                System.out.println("Error "+error);
            }
        });


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringResRequest);
    }


    public String calcularTiempoTranscurrido(Date horaIngreso) {
        // Obtener la hora actual
        Calendar calendarActual = Calendar.getInstance();
        Date horaActual = calendarActual.getTime();

        // Calcular la diferencia de tiempo en milisegundos
        long diferenciaMs = horaActual.getTime() - horaIngreso.getTime();

        // Calcular horas y minutos
        long horas = diferenciaMs / (60 * 60 * 1000);
        long minutos = (diferenciaMs / (60 * 1000)) % 60;

        // Construir el mensaje de tiempo transcurrido
        StringBuilder builder = new StringBuilder();
        if (horas > 0) {
            builder.append(horas);
            if (horas == 1) {
                builder.append(" hora ");
            } else {
                builder.append(" horas ");
            }
        }
        if (minutos > 0) {
            builder.append(minutos);
            if (minutos == 1) {
                builder.append(" minuto");
            } else {
                builder.append(" minutos");
            }
        }

        return builder.toString();
    }

    public double calcularCosto(Date horaIngreso, double costoPorHora) {
        Calendar calendarActual = Calendar.getInstance();
        Date horaActual = calendarActual.getTime();

        long diferenciaMs = horaActual.getTime() - horaIngreso.getTime();

        long horas = diferenciaMs / (60 * 60 * 1000);

        double costoTotal = horas * costoPorHora;

        return costoTotal;
    }


}