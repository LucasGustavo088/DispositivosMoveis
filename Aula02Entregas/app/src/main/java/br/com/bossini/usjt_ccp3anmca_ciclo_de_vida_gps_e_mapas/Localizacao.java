package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;
import android.util.Log;

import java.io.Serializable;
import java.util.Date;

public class Localizacao implements Serializable {

    public Fila fila;
    public double latitude;
    public double longitude;
    public Date dataAbertura;

    public Localizacao(Fila fila, double latitude, double longitude, Date dataAbertura) {
        this.fila = fila;

        this.latitude = latitude;
        this.longitude = longitude;
        this.dataAbertura = dataAbertura;
    }

    public Fila getFila() {
        return fila;
    }

    public void setFila(Fila fila) {
        this.fila = fila;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
}
