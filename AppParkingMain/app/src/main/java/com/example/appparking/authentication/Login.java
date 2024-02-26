    package com.example.appparking.authentication;
    
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.Toast;
    
    import androidx.appcompat.app.AppCompatActivity;
    
    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.StringRequest;
    import com.android.volley.toolbox.Volley;
    import com.example.appparking.MainActivity;
    import com.example.appparking.R;
    import com.example.appparking.navbar.navbar_main;
    import com.example.appparking.ui.home.HomeFragment;
    import com.example.appparking.utils.Config;
    import com.google.android.material.textfield.TextInputLayout;
    
    import org.json.JSONException;
    import org.json.JSONObject;
    
    import java.util.HashMap;
    import java.util.Map;
    
    public class Login extends AppCompatActivity {
    
        TextInputLayout campoEmail;
        TextInputLayout campoPassword;
        Button btnIniciarSesion;
        Config dataConfig;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
    
            campoEmail = findViewById(R.id.etqCampoEmail);
            campoPassword = findViewById(R.id.etqCampoPassword);
            btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
    
            dataConfig = new Config(getApplicationContext());
    
            btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = campoEmail.getEditText().getText().toString();
                    String password = campoPassword.getEditText().getText().toString();
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(Login.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                        System.out.println("NO SE INGRESO CORREO O PASSWORD");
                    } else {
                       authentication(email, password);
                    }
                }
            });
        }
    
        private void authentication(String email, String password) {
            System.out.println("ENTRO A AUTHENTICATION");
    
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = dataConfig.getEndPoint("/Aplication/login.php");
    
            StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
    
                @Override
                public void onResponse(String response) {
                    System.out.println("El servidor POST responde con:");
                    System.out.println(response);
                    try {
                        JSONObject datos = new JSONObject(response);
                        System.out.println("LO QUE ME DEVUELVE EL SERVIDOR");
                        System.out.println(datos);

                        if (datos.getBoolean("status")){
                            System.out.println("Usuario encontrado");
                            String estado = datos.getString("estado");
                            //Extraer el id del usuario
                            String id = datos.getString("idusuario");

                            if (estado.equals("activo")) {

                                String rol = datos.getString("rol");
                                if (rol.equals("administrador")) {
                                    cambiarActivityAdmin(id);
                                } else {
                                    cambiarActivityVendedor(id);
                                }
                            }else {
                                Toast.makeText(getApplicationContext(),"Usuario inactivo",Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),"Usuario no encontrado",Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("El servidor POST responde con un error:");
                    System.out.println(error.getMessage());
                }
    
            }){
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user", email);
                    params.put("pass", password);
    
                    return params;
                }
            };
    
            queue.add(solicitud);
    
        }

        private void cambiarActivityAdmin(String id){
            SharedPreferences archivo = getSharedPreferences( "app_parking", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = archivo.edit();
            editor.putString("id_usuario", id);
            editor.apply();

            Intent intencion = new Intent(getApplicationContext(), MainActivity.class );
            startActivity(intencion);
            finish();
        }

        private void cambiarActivityVendedor(String id){
            SharedPreferences archivo = getSharedPreferences( "app_parking", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = archivo.edit();
            editor.putString("id_usuario", id);
            editor.apply();

            Intent intencion = new Intent(getApplicationContext(), navbar_main.class );
            startActivity(intencion);
            finish();
        }
    
    
    
    }