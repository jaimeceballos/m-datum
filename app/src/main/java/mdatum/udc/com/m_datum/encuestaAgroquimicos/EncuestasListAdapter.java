package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.Encuesta;

/**
 * Created by jaime on 30/11/17.
 */

public class EncuestasListAdapter extends RecyclerView.Adapter<EncuestasListAdapter.EncuestasListViewHolder> {

    List<Encuesta> encuestas;
    Activity activity;

    public EncuestasListAdapter(List<Encuesta> encuestas, Activity activity){
        this.encuestas = encuestas;
        this.activity = activity;
    }

    @Override
    public EncuestasListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se encarga de inflar el layout y lo va a pasar al viewholder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_encuesta_list,parent,false);
        return new EncuestasListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EncuestasListViewHolder holder, int position) {
        //asocia cada elemento de la lista a cada view

        Encuesta encuesta = encuestas.get(position);

        holder.tv_nro_encuesta_cnt.setText(encuesta.getId().toString());
        holder.tv_fecha_cnt.setText(encuesta.getFecha());
        holder.tv_establecimiento_cnt.setText(encuesta.getEstablecimientoRelated().getNombre());
        
        holder.imgSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Pulso sync", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista

        return encuestas.size();
    }

    /**
     * Este adaptador es el que hace el binding entre
     * el layout del recycler view y sus datos, Contiene
     * una clase estatica que va a manejar cada uno de sus elementos
     * es una clase view holder, es la encargada de darle vida a los
     * elementos del recycler view
     */

    public static class EncuestasListViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nro_encuesta_cnt;
        private TextView tv_fecha_cnt;
        private TextView tv_establecimiento_cnt;
        private ImageButton imgSync;

        public EncuestasListViewHolder(View itemView) {
            super(itemView);

            tv_nro_encuesta_cnt = (TextView)itemView.findViewById(R.id.tv_nro_encuesta_cnt);
            tv_fecha_cnt = (TextView)itemView.findViewById(R.id.tv_fecha_cnt);
            tv_establecimiento_cnt = (TextView)itemView.findViewById(R.id.tv_establecimiento_cnt);
            imgSync = (ImageButton) itemView.findViewById(R.id.img_sync);
        }


    }

}
