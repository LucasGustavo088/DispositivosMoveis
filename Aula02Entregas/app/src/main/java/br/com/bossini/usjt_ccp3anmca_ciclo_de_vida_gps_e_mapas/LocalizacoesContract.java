package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import android.provider.BaseColumns;

public class LocalizacoesContract {

    private LocalizacoesContract() {

    }

    public static class LocalizacaoContract implements BaseColumns {
        public static final String DROP_TABLE = String.format("DROP TABLE%s", LocalizacaoContract.TABLE_NAME);
        public static final String TABLE_NAME = "tb_localizacao";
        public static final String COLUMN_NAME_ID = "id_localizacao";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_DATA_ABERTURA = "data_abertura";
    }

    public static String createTableLocalizacao (){
        return String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER);",
            LocalizacaoContract.TABLE_NAME,
            LocalizacaoContract.COLUMN_NAME_ID,
            LocalizacaoContract.COLUMN_NAME_LATITUDE,
            LocalizacaoContract.COLUMN_NAME_LONGITUDE,
            LocalizacaoContract.COLUMN_NAME_DATA_ABERTURA
        );
    }


}
