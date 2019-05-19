package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalizacaoDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "localizacao.db";

    private static final int DB_VERSION = 1;
    LocalizacaoDBHelper (Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalizacoesContract.createTableLocalizacao());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL(LocalizacoesContract.LocalizacaoContract.DROP_TABLE);
        onCreate(db);
    }
}
