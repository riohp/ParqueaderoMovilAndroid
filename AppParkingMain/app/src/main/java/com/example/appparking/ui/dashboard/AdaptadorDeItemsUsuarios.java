package com.example.appparking.ui.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appparking.R;

import java.util.List;

public class AdaptadorDeItemsUsuarios extends RecyclerView.Adapter<AdaptadorDeItemsUsuarios.ViewHolder> {

    List<Usuario> listaUsuarios;

    Context contexto;
    public AdaptadorDeItemsUsuarios(List<Usuario> listaUsuarios) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etqIdentificacion = itemView.findViewById(R.id.etqIdentificacion);
            etpNombres = itemView.findViewById(R.id.etpNombres);
            etpEmail = itemView.findViewById(R.id.etqEmail);
            etpEstado = itemView.findViewById(R.id.etpEstado);
            etqRol = itemView.findViewById(R.id.etqRol);
            cardViewUser = itemView.findViewById(R.id.cardViewUser);
        }

        public void cargarDatos(Usuario persona) {
            etpNombres.setText(persona.getNombres());
            etpEmail.setText(persona.getEmail());
            etqIdentificacion.setText(persona.getIdentificacion());
            etqRol.setText(persona.getRol());
            if (persona.getEstado().equals("activo")) {
                cardViewUser.setCardBackgroundColor(Color.parseColor("#F0FEE4"));
                etpEstado.setBackgroundColor(Color.GREEN);
            } else {
                etpEstado.setBackgroundColor(Color.RED);
            }
        }
    }
}

