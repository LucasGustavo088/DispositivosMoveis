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
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            vh = new ViewHolder();
            vh.latitudeLocalizacaoTextView = convertView.findViewById(R.id.latitudeLocalizacaoTextView);
            vh.latitudeLocalizacaoTextView = convertView.findViewById(R.id.latitudeLocalizacaoTextView);
            vh.dataAberturaLocalizacaoTextView = convertView.findViewById(R.id.dataAberturaTextView);
            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();

        vh.latitudeLocalizacaoTextView.setText(String.valueOf(localizacaoDaVez.getLatitude()));
        vh.latitudeLocalizacaoTextView.setText(String.valueOf(localizacaoDaVez.getLongitude()));
        vh.dataAberturaLocalizacaoTextView.setText(DateHelper.format(localizacaoDaVez.getDataAbertura()));

        return convertView;
    }
}
