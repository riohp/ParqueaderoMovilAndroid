package com.example.appparking.ui.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;
import java.util.Map;

public class AdaptadorDeItemsUsuarios extends RecyclerView.Adapter<AdaptadorDeItemsUsuarios.ViewHolder> {

    List<Usuario> listaUsuarios;
    Context contexto;

    public AdaptadorDeItemsUsuarios(Context contexto, List<Usuario> listaUsuarios) {
        this.contexto = contexto;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public AdaptadorDeItemsUsuarios.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_usuarios, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDeItemsUsuarios.ViewHolder holder, int position) {

        Usuario temporal = listaUsuarios.get(position);
        holder.cargarDatos(temporal);

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void actualizarUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView etqIdentificacion;
        TextView etpNombres;
        TextView etpEmail;

        View etpEstado;
        CardView cardViewUser;
        TextView etqRol;
        Config dataConfig;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etqIdentificacion = itemView.findViewById(R.id.etqIdentificacion);
            etpNombres = itemView.findViewById(R.id.etpNombres);
            etpEmail = itemView.findViewById(R.id.etqEmail);
            etpEstado = itemView.findViewById(R.id.etpEstado);
            etqRol = itemView.findViewById(R.id.etqRol);
            cardViewUser = itemView.findViewById(R.id.cardViewUser);
            dataConfig = new Config(contexto.getApplicationContext());
        }

        public void cargarDatos(Usuario persona) {
            etpNombres.setText(persona.getNombres());
            etpEmail.setText(persona.getEmail());
            etqIdentificacion.setText(persona.getIdentificacion());
            etqRol.setText(persona.getRol());
            etpEstado.setTag(persona.getId());
            if (persona.getEstado().equals("activo")) {
                cardViewUser.setCardBackgroundColor(Color.parseColor("#F0FEE4"));
                etpEstado.setBackgroundColor(Color.GREEN);
            } else {
                etpEstado.setBackgroundColor(Color.RED);
            }

            etpEstado.setOnClickListener(new View.OnClickListener(){

                public  void onClick(View v){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mostrarAlertaConfirmacion(contexto, position);
                    }
                }
            });

        }

        public void mostrarAlertaConfirmacion(Context context, int position) {
            String id = (String) etpEstado.getTag();
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.success_dialog);

            TextView tvTitle = dialog.findViewById(R.id.tvTitle);
            tvTitle.setText("Deshabilitar");
            TextView successDesc = dialog.findViewById(R.id.successDesc);
            successDesc.setText("¿Estás seguro?");

            Button successDone = dialog.findViewById(R.id.successDone);
            Button successCancel = dialog.findViewById(R.id.successCancel);

            successDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cambiarEstado(id, position);
                    dialog.dismiss();
                }
            });

            successCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

        public void cambiarEstado(String id, int position) {
            RequestQueue queue = Volley.newRequestQueue(contexto.getApplicationContext());
            String url = dataConfig.getEndPoint("/Usuarios/UpdateStatus.php");

            StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    System.out.println("El servidor POST responde con:");
                    System.out.println(response);
                    try {
                        JSONObject datos = new JSONObject(response);
                        System.out.println("LO QUE ME DEVUELVE EL SERVIDOR");
                        System.out.println(datos);

                        if (datos.getBoolean("status")) {
                            // Actualizar el estado en la lista local
                            listaUsuarios.get(position).setEstado("inactivo");
                            // Notificar al adaptador que el elemento en la posición dada ha cambiado
                            notifyItemChanged(position);
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
                    params.put("idusuario", id);

                    return params;
                }
            };

            queue.add(solicitud);
        }

    }
}
