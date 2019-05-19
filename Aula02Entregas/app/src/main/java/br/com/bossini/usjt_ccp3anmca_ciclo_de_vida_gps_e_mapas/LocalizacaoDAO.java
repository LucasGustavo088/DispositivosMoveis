package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Locale;

public class LocalizacaoDAO {

    private Context context;
    public LocalizacaoDAO(Context context) {
        this.context = context;
    }


    public void insertLocalizacao(Localizacao localizacao) {

        LocalizacaoDBHelper dbHelper = new LocalizacaoDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String template = "INSERT INTO %s (%s, %s, %d) VALUES (‘%s’, ‘%s’, %d);";

        String.format(
                Locale.getDefault(),
                template,
                LocalizacoesContract.LocalizacaoContract.TABLE_NAME,
                LocalizacoesContract.LocalizacaoContract.COLUMN_NAME_LATITUDE,
                LocalizacoesContract.LocalizacaoContract.COLUMN_NAME_LONGITUDE,
                LocalizacoesContract.LocalizacaoContract.COLUMN_NAME_DATA_ABERTURA,
                localizacao.getLatitude(),
                localizacao.getLongitude(),
                localizacao.getDataAbertura()
        );

        StringBuilder sb = new StringBuilder("");
    }

}
