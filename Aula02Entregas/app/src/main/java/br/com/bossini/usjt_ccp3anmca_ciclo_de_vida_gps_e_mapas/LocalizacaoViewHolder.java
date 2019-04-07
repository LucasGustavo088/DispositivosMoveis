package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LocalizacaoViewHolder extends RecyclerView.ViewHolder {
    public TextView latitudeLocalizacaoTextView;
    public TextView longitudeLocalizacaoTextView;
    public TextView dataAberturaLocalizacaoTextView;

    public LocalizacaoViewHolder(View v) {
        super(v);
        latitudeLocalizacaoTextView = v.findViewById(R.id.latitudeLocalizacaoTextView);
        longitudeLocalizacaoTextView = v.findViewById(R.id.longitudeLocalizacaoTextView);
        dataAberturaLocalizacaoTextView = v.findViewById(R.id.dataAberturaTextView);
    }


}
