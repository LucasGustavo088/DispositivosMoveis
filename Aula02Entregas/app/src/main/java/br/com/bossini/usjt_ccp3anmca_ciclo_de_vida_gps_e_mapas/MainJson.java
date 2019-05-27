package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

public class MainJson {
    private double temp_min;
    private double temp_max;
    private double humidity;

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
