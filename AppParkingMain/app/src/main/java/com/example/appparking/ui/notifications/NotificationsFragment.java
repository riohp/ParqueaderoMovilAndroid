package com.example.appparking.ui.notifications;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import org.json.JSONObject;
import org.json.JSONException;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentNotificationsBinding;
import com.example.appparking.utils.Config;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private AutoCompleteTextView autoCompleteTextView;
    private TextInputLayout campoIdentificacion, campoNombre, campoCorreo, campoRol, campoPassword;
    private Button btnAgregarUsuario;
    private Config dataConfig;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dataConfig = new Config(requireActivity().getApplicationContext());

        campoIdentificacion = root.findViewById(R.id.etpcampoIdentificacion);
        campoNombre = root.findViewById(R.id.etpcampoNombre);
        campoCorreo = root.findViewById(R.id.etpcampoCorreo);
        campoRol = root.findViewById(R.id.etpcampoRol);
        campoPassword = root.findViewById(R.id.etpcampoPassword);
        btnAgregarUsuario = root.findViewById(R.id.btnAgregarUsuario);



        autoCompleteTextView = root.findViewById(R.id.etpRol);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.opciones_roles, android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(adapter);

        btnAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identificacion = campoIdentificacion.getEditText().getText().toString();
                String nombre = campoNombre.getEditText().getText().toString();
                String correo = campoCorreo.getEditText().getText().toString();
                String rol = campoRol.getEditText().getText().toString();
                String contrasena = campoPassword.getEditText().getText().toString();

                if (TextUtils.isEmpty(identificacion) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(correo) || TextUtils.isEmpty(rol) || TextUtils.isEmpty(contrasena)) {
                    Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    verificarUsuario(identificacion, nombre, correo, rol, contrasena);
                }
            }
        });
        return root;
    }

    private void verificarUsuario(String identificacion, String nombre, String correo, String rol, String contrasena) {
        RequestQueue queue = Volley.newRequestQueue(requireActivity().getApplicationContext());
        String url = dataConfig.getEndPoint("/Usuarios/Verificar.php");

        StringRequest solicitud = new StringRequest(Request.Method.GET, url + "?identificacion=" + identificacion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String estado = jsonResponse.getString("estado");

                    if (estado.equals("existe")) {
                        Toast.makeText(getActivity(), "El usuario ya está registrado", Toast.LENGTH_SHORT).show();
                    } else {
                        agregarUsuario(identificacion, nombre, correo, rol, contrasena);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error al verificar usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error al verificar usuario: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(solicitud);
    }

    private void agregarUsuario(String identificacion, String nombre, String correo, String rol, String contrasena) {
        RequestQueue queue = Volley.newRequestQueue(requireActivity().getApplicationContext());
        String url = dataConfig.getEndPoint("/Usuarios/Insert_U.php");

        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "Usuario agregado correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error al agregar usuario: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("identificacion", identificacion);
                params.put("nombreusuario", nombre);
                params.put("correoelectronico", correo);
                params.put("rol", rol);
                params.put("contrasena", contrasena);
                params.put("estado", "activo");
                return params;
            }
        };

        queue.add(solicitud);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


