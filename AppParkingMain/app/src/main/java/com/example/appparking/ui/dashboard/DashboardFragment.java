package com.example.appparking.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appparking.R;
import com.example.appparking.utils.Config;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.search.SearchBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    EditText campoBusqueda;

    RecyclerView recyclerUsarios;
    AdaptadorDeItemsUsuarios adaptador;

    Config dataConfig;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        campoBusqueda = root.findViewById(R.id.campo_busqueda);
        recyclerUsarios = root.findViewById(R.id.recyclerUsarios);
        recyclerUsarios.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptador = new AdaptadorDeItemsUsuarios(new ArrayList<>());
        recyclerUsarios.setAdapter(adaptador);
        dataConfig = new Config(requireActivity().getApplicationContext());


        obtenerVendedores();

        return root;
    }

    private void obtenerVendedores() {
        String url = dataConfig.getEndPoint("/Usuarios/Obtener_Vendedores.php");

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Usuario> listaUsuarios = procesarRespuesta(response);
                adaptador.actualizarUsuarios(listaUsuarios);

                System.out.println("api vendedores: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error al obtener vendedores: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        queue.add(solicitud);
    }


    private List<Usuario> procesarRespuesta(String response) {
        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("registros");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject usuarioObject = jsonArray.getJSONObject(i);


                String identificacion = usuarioObject.getString("identificacion");
                String nombreUsuario = usuarioObject.getString("nombreusuario");
                String correoElectronico = usuarioObject.getString("correoelectronico");
                String estado = usuarioObject.getString("estado");
                String rol = usuarioObject.getString("rol");

                if (estado.equals("1")) {
                    estado = "Activo";

                } else {
                    estado = "Inactivo";
                }
                Usuario usuario = new Usuario(identificacion, nombreUsuario, correoElectronico, estado, rol);
                listaUsuarios.add(usuario);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }


}
