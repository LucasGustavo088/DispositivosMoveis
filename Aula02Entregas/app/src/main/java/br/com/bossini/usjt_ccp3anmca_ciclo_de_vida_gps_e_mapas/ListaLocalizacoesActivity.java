package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListaLocalizacoesActivity extends AppCompatActivity {

    private ListView chamadosListView;
    private TextView localizacaoTextView;
    private Location localizacaoAtual;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int REQUEST_CODE_GPS = 1001;
    ArrayList<String> lista = new ArrayList<>();

    public class LatLong {
        public double latitude;
        public double longitude;
    }

    public void atualizarView() {
        atualizarLocalizacoes();
        final List <String> chamados = lista;
        chamadosListView = findViewById(R.id.chamadosListView);
        chamadosListView.setAdapter(null);
        Collections.reverse(chamados);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chamados);
        chamadosListView.setAdapter(adapter);


        chamadosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.chamadosListView);
                String selectedFromList = (String) chamadosListView.getItemAtPosition(position);
                selectedFromList = selectedFromList.replace("Lat: ", "").replace(" Long: ", "");

                double latitude = Double.parseDouble(selectedFromList.split(",")[0]);
                double longitude = Double.parseDouble(selectedFromList.split(",")[1]);
                Log.d(selectedFromList, selectedFromList);
                Uri uri = Uri.parse(
                        String.format("geo:%f,%f", latitude, longitude));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_localizacoes);




        locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Log.d("antesmudou","antesmudou");
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                Log.d("onLocationChanged","onLocationChanged");
                localizacaoAtual = location;
                /*double lat = location.getLatitude();
                double lon = location.getLongitude();
                localizacaoTextView.setText(
                        String.format("Lat: %f, Long: %f", lat, lon)
                );*/
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("onStatusChanged", "onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("onProviderEnabled", "onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("onProviderDisabled", "onProviderDisabled");
            }
        };

        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                Log.d("atualizado", "atualizado");
                atualizarView();
                handler.postDelayed(this,2000); // set time here to refresh textView
            }
        });
    }

    public void atualizarLocalizacoes(){

        if(lista.isEmpty()) {
            for(int i = 0; i < 50; i++) {
                //double lat = this.obterLatitudeLongitude().latitude;
                //double lon = this.obterLatitudeLongitude().longitude;
                double lat = 0.00;
                double lon = 0.00;
                if(localizacaoAtual != null) {
                    lat = localizacaoAtual.getLatitude();
                    lon = localizacaoAtual.getLongitude();
                }

                lista.add(String.format("Lat: %f, Long: %f", lat, lon));
            }
        } else {
            //double lat = this.obterLatitudeLongitude().latitude;
            //double lon = this.obterLatitudeLongitude().longitude;
            double lat = 0.00;
            double lon = 0.00;
            if(localizacaoAtual != null) {
                lat = localizacaoAtual.getLatitude();
                lon = localizacaoAtual.getLongitude();
            }

            lista.remove(lista.size() - 1);
            lista.add(String.format("Lat: %f, Long: %f", lat, lon));
        }


    }

    public LatLong obterLatitudeLongitude() {
        LatLong latlong = new LatLong();
        Random r = new Random();
        latlong.latitude = r.nextDouble();
        latlong.longitude = r.nextDouble();

        return latlong;
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
                            0,
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
        Log.d("porcaria","porcaria");

        if (ActivityCompat.
                checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String []{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_GPS);
        }
    }
}