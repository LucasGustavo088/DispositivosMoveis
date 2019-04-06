package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public static String format(Date date) {
        return sdf.format(date);
    }
}
