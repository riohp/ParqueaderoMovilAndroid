package com.example.appparking.QueryParking;

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

public class AdaptadorQueryParking extends RecyclerView.Adapter<AdaptadorQueryParking.ViewHolder> {
    List<Parkings> listaParkings;
    private OnItemClickListener listener;

    public AdaptadorQueryParking(List<Parkings> listaParkings) {
        this.listaParkings = listaParkings;
    }


    @NotNull
    @Override
    public AdaptadorQueryParking.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_query_parking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdaptadorQueryParking.ViewHolder holder, int position) {
        Parkings temporal = listaParkings.get(position);
        holder.cargarDatos(temporal);
    }

    @Override
    public int getItemCount() {
        return listaParkings.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(QueryParking listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView etqNombreParking;
        TextView etqDireccionParking;
        CardView cardViewParking;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            etqNombreParking = itemView.findViewById(R.id.etqNombreParking);
            etqDireccionParking = itemView.findViewById(R.id.etqDirrecionparking);
            cardViewParking = itemView.findViewById(R.id.cardViewParking);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }

        public void cargarDatos(Parkings temporal) {
            etqNombreParking.setText(temporal.getNombre());
            etqDireccionParking.setText(temporal.getDireccion());
        }
    }
}
