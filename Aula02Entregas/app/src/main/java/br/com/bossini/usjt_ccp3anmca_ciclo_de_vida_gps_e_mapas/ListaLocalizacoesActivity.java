package br.com.bossini.usjt_ccp3anmca_ciclo_de_vida_gps_e_mapas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaLocalizacoesActivity extends AppCompatActivity {

    private ListView localizacoesListView;
    private TextView localizacaoTextView;
    private Location localizacaoAtual;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int REQUEST_CODE_GPS = 1001;
    List <Localizacao> lista = new ArrayList<>();
    private RecyclerView localizacoesRecyclerView;

    public class LatLong {
        public double latitude;
        public double longitude;
    }

    public void atualizarView() {
        gerarLocalizacoes();

        setContentView(R.layout.activity_lista_localizacoes);
        localizacoesRecyclerView = findViewById(R.id.localizacoesRecyclerView);
        localizacoesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*ChamadoArrayAdapter adapter =
                new ChamadoArrayAdapter(this, chamados);*/
        LocalizacaoRecyclerViewAdapter adapter =
                new LocalizacaoRecyclerViewAdapter (lista);
        localizacoesRecyclerView.setAdapter(adapter);

//        localizacoesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView textView = view.findViewById(R.id.localizacoesListView);
//                Localizacao selectedFromList = lista.get(position);
//
//                Uri uri = Uri.parse(
//                        String.format("geo:%f,%f", selectedFromList.getLatitude(), selectedFromList.getLongitude()));
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                intent.setPackage("com.google.android.apps.maps");
//                startActivity(intent);
//            }
//        });
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_localizacoes);

        locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                localizacaoAtual = location;

                atualizarView();
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

    }

    public void gerarLocalizacoes(){

        if(lista.isEmpty()) {
            for(int i = 0; i < 50; i++) {

                double lat = 0.00;
                double lon = 0.00;
                if(localizacaoAtual != null) {
                    lat = localizacaoAtual.getLatitude();
                    lon = localizacaoAtual.getLongitude();
                }

                //lista.add(String.format("Lat: %f, Long: %f", lat, lon));
                lista.add(
                        new Localizacao (
                                new Fila ("Nome da fila"),
                                lat,
                                lon,
                                new Date()
                        ));
            }
        } else {

            double lat = 0.00;
            double lon = 0.00;
            if(localizacaoAtual != null) {
                lat = localizacaoAtual.getLatitude();
                lon = localizacaoAtual.getLongitude();
            }

            Localizacao novaLocalizacao = new Localizacao (
                    new Fila ("Nome da fila"),
                    lat,
                    lon,
                    new Date()
            );

            Context context = null;
            LocalizacaoDAO dao = new LocalizacaoDAO(context);
            dao.insertLocalizacao(novaLocalizacao);

            lista.add(novaLocalizacao);

            //lista.add(0, String.format("Lat: %f, Long: %f", lat, lon));
            lista.remove(lista.size() - 1);
        }
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

        if (ActivityCompat.
                checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String []{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_GPS);
        }
    }
}