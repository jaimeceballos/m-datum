package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.EncuestaEstablecimiento;

/**
 * Created by jaime on 30/11/17.
 */

public class EncuestasListAdapter extends RecyclerView.Adapter<EncuestasListAdapter.EncuestasListViewHolder> {

    ArrayList<EncuestaEstablecimiento> encuestas;

    public EncuestasListAdapter(ArrayList<EncuestaEstablecimiento> encuestaEstablecimientos){
        this.encuestas = encuestaEstablecimientos;
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

        EncuestaEstablecimiento encuestaEstablecimiento = encuestas.get(position);

        holder.tv_nro_encuesta_cnt.setText(encuestaEstablecimiento.getId_encuesta().toString());
        holder.tv_fecha_cnt.setText("25/11/2017");
        holder.tv_establecimiento_cnt.setText(encuestaEstablecimiento.getEstablecimiento().toString());
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

        public EncuestasListViewHolder(View itemView) {
            super(itemView);

            tv_nro_encuesta_cnt = (TextView)itemView.findViewById(R.id.tv_nro_encuesta_cnt);
            tv_fecha_cnt = (TextView)itemView.findViewById(R.id.tv_fecha_cnt);
            tv_establecimiento_cnt = (TextView)itemView.findViewById(R.id.tv_establecimiento_cnt);
        }


    }

}
