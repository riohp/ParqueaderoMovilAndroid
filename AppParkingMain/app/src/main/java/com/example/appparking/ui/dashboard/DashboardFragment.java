package com.example.appparking.ui.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    TextInputEditText campoBusqueda;
    RecyclerView recyclerUsuarios;
    AdaptadorDeItemsUsuarios adaptador;
    List<Usuario> listaCompletaUsuarios;

    Config dataConfig;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        campoBusqueda = root.findViewById(R.id.campo_busqueda);
        recyclerUsuarios = root.findViewById(R.id.recyclerUsarios);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptador = new AdaptadorDeItemsUsuarios(new ArrayList<>());
        recyclerUsuarios.setAdapter(adaptador);
        dataConfig = new Config(requireActivity().getApplicationContext());

        obtenerVendedores();

        return root;
    }

    private void obtenerVendedores() {
        String url = dataConfig.getEndPoint("/Usuarios/Obtener_Vendedores.php");

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listaCompletaUsuarios = procesarRespuesta(response);
                adaptador.actualizarUsuarios(listaCompletaUsuarios);

                System.out.println("viendo por aqui on" + listaCompletaUsuarios);
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

                Usuario usuario = new Usuario(identificacion, nombreUsuario, correoElectronico, estado, rol);
                listaUsuarios.add(usuario);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }

    //crear una vista para el fragmento de busqueda
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        campoBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textoBusqueda = charSequence.toString().toLowerCase().trim();
                List<Usuario> usuariosFiltrados = filtrarUsuarios(textoBusqueda);
                adaptador.actualizarUsuarios(usuariosFiltrados);

                System.out.println("viendo por aqui" + usuariosFiltrados);


            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }


    private List<Usuario> filtrarUsuarios(String textoBusqueda) {
        List<Usuario> usuariosFiltrados = new ArrayList<>();

        //obviamente si no esta null la lsita sigue funkando
        if (listaCompletaUsuarios != null) {
            for (Usuario usuario : listaCompletaUsuarios) {
                if (usuario.getNombres().toLowerCase().contains(textoBusqueda) ||
                        usuario.getIdentificacion().toLowerCase().contains(textoBusqueda)) {
                    usuariosFiltrados.add(usuario);
                }
            }
        }
        return usuariosFiltrados;
    }

    @Override
    public void onResume() {
        super.onResume();
        obtenerVendedores();
    }

}
