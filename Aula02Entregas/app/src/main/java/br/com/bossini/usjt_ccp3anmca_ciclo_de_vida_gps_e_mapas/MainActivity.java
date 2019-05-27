package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView weatherRecyclerView;
    private WeatherAdapter weatherAdapter;
    private List<Weather> previsoes;
    private EditText locationEditText;
    private RequestQueue requestQueue;
    private Location localizacaoAtual;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int REQUEST_CODE_GPS = 1001;
    List <Localizacao> lista = new ArrayList<>();
    private RecyclerView localizacoesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obter latitude e longitude
        locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                localizacaoAtual = location;
                obtemPrevisoesV5();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //Weather
        requestQueue = Volley.newRequestQueue(this);
        locationEditText = findViewById(R.id.locationEditText);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weatherRecyclerView =
                findViewById(R.id.weatherRecyclerView);
        previsoes = new ArrayList<>();
        previsoes.add(new Weather(
                500,
                37,
                38,
                0.7,
                "Teste 1",
                ""
        ));
//        previsoes.add(new Weather(
//                554345822,
//                "Teste 2",
//                37,
//                38,
//                0.7,
//                ""
//        ));
        weatherAdapter = new WeatherAdapter(this, previsoes);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        weatherRecyclerView.setAdapter(weatherAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeCidade =
                        locationEditText.getEditableText().toString();
                obtemPrevisoesV5();
            }
        });
    }

    public String obtemEnderecoAPI() {
        double lat = 0.00;
        double lon = 0.00;
        if(localizacaoAtual != null) {
            lat = localizacaoAtual.getLatitude();
            lon = localizacaoAtual.getLongitude();
        }

        String endereco = getString(
                R.string.web_service_url,
                String.valueOf(lat),
                String.valueOf(lon),
                getString(R.string.api_key)
        );

        return endereco;
    }
    public void obtemPrevisoesV5 (){
        String endereco = obtemEnderecoAPI();

        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET,
                endereco,
                null,
                (response) ->{
                    lidaComJSON(response);
                },
                (error) -> {
                    Toast.makeText(this,
                            getString(R.string.connect_error) + error.getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                }
        );
        requestQueue.add(req);

    }
    public void obtemPrevisoesV4 (){
        String endereco = obtemEnderecoAPI();
        new ObtemPrevisoes().execute(endereco);
    }

    public void obtemPrevisoesV3 (){
        new Thread(()-> {
            String endereco = obtemEnderecoAPI();
            try {
                URL url = new URL (endereco);
                HttpURLConnection conn =
                        (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder resultado = new StringBuilder ("");
                String aux = null;
                while ((aux = reader.readLine()) != null)
                    resultado.append(aux);
                reader.close();
                conn.disconnect();
                runOnUiThread(() -> {
                    lidaComJSON(resultado.toString());
                    //Toast.makeText(this, resultado.toString(), Toast.LENGTH_SHORT).show();
                });
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    class ObtemPrevisoes extends AsyncTask <String, Void, String>{

        @Override
        protected String doInBackground(String... enderecos) {
            String endereco = enderecos[0];
            try {
                URL url = new URL (endereco);
                HttpURLConnection conn =
                        (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder resultado = new StringBuilder ("");
                String aux = null;
                while ((aux = reader.readLine()) != null)
                    resultado.append(aux);
                reader.close();
                conn.disconnect();
                return resultado.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String resultado) {
            lidaComJSON(resultado);
        }
    }

    public void lidaComJSON (JSONObject resultado){
        try {
            previsoes.clear();
            Gson gson = new GsonBuilder().create();
            JSONArray list = resultado.getJSONArray("list");

            if (list.length() > 0) {
                ItemWeatherJson[] weathers = gson.fromJson(list.toString(), ItemWeatherJson[].class);

                for(ItemWeatherJson itemWeather: weathers){
                    itemWeather.getDt();
                }
            }

            for (int i = 0; i < list.length(); i++){

                JSONObject caraDaVez = list.getJSONObject(i);
                String caraDaVezJsonString = caraDaVez.toString();
                ItemWeatherJson itemWeatherJson = gson.fromJson(caraDaVezJsonString, ItemWeatherJson.class);

                long dt = itemWeatherJson.getDt();
                JSONObject main = caraDaVez.getJSONObject("main");
                MainJson mainJson = gson.fromJson(main.toString(), MainJson.class);

                double temp_min = mainJson.getTemp_min();
                double temp_max = mainJson.getTemp_max();
                double humidity = mainJson.getHumidity();

                String description =
                        caraDaVez.
                                getJSONArray("weather").
                                getJSONObject(0).
                                getString("description");
                String icon =
                        caraDaVez.
                                getJSONArray("weather").
                                getJSONObject(0).
                                getString("icon");
                Weather w =
                        new Weather(dt, temp_min, temp_max, humidity, description, icon);
                previsoes.add(w);
            }
            weatherAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void lidaComJSON (String resultado){
        try {
            lidaComJSON(new JSONObject(resultado));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void obtemPrevisoesV2 (){
        new Thread(()-> {
            String endereco = obtemEnderecoAPI();
            try {
                URL url = new URL (endereco);
                HttpURLConnection conn =
                        (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                Toast.makeText(this, "acabou...", Toast.LENGTH_SHORT).show();

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void obtemPrevisoesV1 (){
        String endereco = obtemEnderecoAPI();
        try {
            URL url = new URL (endereco);
            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();

        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public class LatLong {
        public double latitude;
        public double longitude;
    }

    @Override
    protected void onStop(){
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GPS){
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            2000,
                            0,
                            locationListener);
                }

            }
            else{
                Toast.makeText(this,
                        getString(R.string.no_gps_no_app),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String []{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_GPS);
        }
    }
}