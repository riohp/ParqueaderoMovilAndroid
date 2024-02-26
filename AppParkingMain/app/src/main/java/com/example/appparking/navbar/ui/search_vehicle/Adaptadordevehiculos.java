package com.example.appparking.navbar.ui.search_vehicle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appparking.R;

import java.util.List;

public class Adaptadordevehiculos extends RecyclerView.Adapter<Adaptadordevehiculos.ViewHolder> {

    static List<Vehiculo> listaVehiculos;

    Context contexto;
    public Adaptadordevehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }



    @NonNull
    @Override
    public Adaptadordevehiculos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_carros, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adaptadordevehiculos.ViewHolder holder, int position) {

        Vehiculo temporal = listaVehiculos.get(position);
        holder.cargarDatos(temporal);

    }

    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView etqPlaca;
        TextView etpTipo;
        TextView etpMarca;
        TextView etpModelo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etqPlaca = itemView.findViewById(R.id.etqPlaca);
            etpTipo = itemView.findViewById(R.id.etpTipo);
            etpMarca = itemView.findViewById(R.id.etpMarca);
            etpModelo = itemView.findViewById(R.id.etpModelo);

        }

        public void cargarDatos(Vehiculo temporal) {
            etqPlaca.setText(temporal.getPlaca());
            etpMarca.setText(temporal.getMarca());
            etpModelo.setText(temporal.getModelo());
            etpTipo.setText(temporal.getTipo());
        }
    }
}
