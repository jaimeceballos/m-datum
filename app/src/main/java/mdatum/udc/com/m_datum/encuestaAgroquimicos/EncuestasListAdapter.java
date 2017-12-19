package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.Encuesta;

/**
 * Created by jaime on 30/11/17.
 */

public class EncuestasListAdapter extends RecyclerView.Adapter<EncuestasListAdapter.EncuestasListViewHolder> {

    private List<Encuesta> encuestas;
    private Activity activity;
    private RecyclerView mRecyclerView;

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
    public void onBindViewHolder(EncuestasListViewHolder holder, final int position) {
        //asocia cada elemento de la lista a cada view

        final Encuesta encuesta = encuestas.get(position);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

        holder.tvNroEncuestaCnt.setText(encuesta.getId().toString());
        holder.tvFechaCnt.setText(encuesta.getFecha());
        holder.tvEstablecimientoCnt.setText(encuesta.getEstablecimientoRelated().getNombre());
        if(!encuesta.getIs_finished()){
            holder.imgNotFinalized.setVisibility(View.VISIBLE);
            holder.imgFinalized.setVisibility(View.GONE);
            holder.imgSynchronized.setVisibility(View.GONE);
            holder.imgNotFinalized.setOnClickListener(listener);
            holder.tvNroEncuestaCnt.setOnClickListener(listener);
            holder.tvFechaCnt.setOnClickListener(listener);
            holder.tvEstablecimientoCnt.setOnClickListener(listener);
            holder.tvEstablecimientoLbl.setOnClickListener(listener);
            holder.tvNroEncuestaLbl.setOnClickListener(listener);
            holder.tvFechaLbl.setOnClickListener(listener);
        }else if(encuesta.getIs_finished() && !encuesta.getIsSincronized()){
            holder.imgNotFinalized.setVisibility(View.GONE);
            holder.imgFinalized.setVisibility(View.VISIBLE);
            holder.imgSynchronized.setVisibility(View.GONE);
        }else{
            holder.imgNotFinalized.setVisibility(View.GONE);
            holder.imgFinalized.setVisibility(View.GONE);
            holder.imgSynchronized.setVisibility(View.VISIBLE);
        }



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

        private TextView tvNroEncuestaCnt,tvNroEncuestaLbl;
        private TextView tvFechaCnt,tvFechaLbl;
        private TextView tvEstablecimientoCnt,tvEstablecimientoLbl;
        private ImageView imgNotFinalized,imgFinalized,imgSynchronized;

        public EncuestasListViewHolder(View itemView) {
            super(itemView);

            tvNroEncuestaCnt        = (TextView) itemView.findViewById(R.id.tv_nro_encuesta_cnt);
            tvNroEncuestaLbl        = (TextView) itemView.findViewById(R.id.tv_nro_encuesta_lbl);
            tvFechaCnt              = (TextView) itemView.findViewById(R.id.tv_fecha_cnt);
            tvFechaLbl              = (TextView) itemView.findViewById(R.id.tv_fecha_lbl);
            tvEstablecimientoCnt    = (TextView) itemView.findViewById(R.id.tv_establecimiento_cnt);
            tvEstablecimientoLbl    = (TextView) itemView.findViewById(R.id.tv_establecimiento_lbl);
            imgNotFinalized         = (ImageView) itemView.findViewById(R.id.img_not_finalized);
            imgFinalized            = (ImageView) itemView.findViewById(R.id.img_finalized);
            imgSynchronized         = (ImageView) itemView.findViewById(R.id.img_synchronized);
        }


    }


}
