package com.example.appparking.ui.parking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appparking.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdaptadorItemsParking extends RecyclerView.Adapter<AdaptadorItemsParking.ViewHolder>{

    List<Parking> listaParking;

    Context contexto;

    public AdaptadorItemsParking(List<Parking> listaParking) {
        this.listaParking = listaParking;
    }
    @NotNull
    @Override
    public AdaptadorItemsParking.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_parking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull AdaptadorItemsParking.ViewHolder holder, int position) {
        Parking temporal = listaParking.get(position);
        holder.cargarDatos(temporal);
    }

    @Override
    public int getItemCount() {
        return listaParking.size();
    }

    public void actualizarParking(List<Parking> listaParking) {
        this.listaParking = listaParking;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView plazas_ocupadas;
        TextView nombre_parqueadero;
        TextView direccion_parqueadero;

        CardView cardViewParking;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            plazas_ocupadas = itemView.findViewById(R.id.plazas_ocupadas);
            nombre_parqueadero = itemView.findViewById(R.id.nombre_parqueadero);
            direccion_parqueadero = itemView.findViewById(R.id.direccion_parqueadero);
            cardViewParking = itemView.findViewById(R.id.cardViewParking);
        }

        public void cargarDatos(Parking parking) {
            plazas_ocupadas.setText(parking.getPlazas());
            nombre_parqueadero.setText(parking.getNombre());
            direccion_parqueadero.setText(parking.getDireccion());
        }
    }
}
