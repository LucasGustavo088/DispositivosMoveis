package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LocalizacaoArrayAdapter extends ArrayAdapter<Localizacao> {

    public LocalizacaoArrayAdapter(Context context, List< Localizacao > localizacoes) {
        super(context, -1, localizacoes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Localizacao localizacaoDaVez = getItem(position);
        Fila filaDaVez = localizacaoDaVez.getFila();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        View view = inflater.inflate(R.layout.list_item, parent, false);
        TextView latitudeLocalizacaoTextView = view.findViewById(R.id.latitudeLocalizacaoTextView);
        TextView longitudeLocalizacaoTextView = view.findViewById(R.id.longitudeLocalizacaoTextView);
        TextView dataAberturaTextView = view.findViewById(R.id.dataAberturaTextView);

        latitudeLocalizacaoTextView.setText(String.valueOf(localizacaoDaVez.getLatitude()));
        longitudeLocalizacaoTextView.setText(String.valueOf(localizacaoDaVez.getLongitude()));
        dataAberturaTextView.setText(DateHelper.format(localizacaoDaVez.getDataAbertura()));

        return view;
    }
}
