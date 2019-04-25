package com.example.carles.navigationdraw;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LlistaLlocs.OnFragmentInteractionListener,
        AfegirLloc.OnFragmentInteractionListener, EditarLloc.OnFragmentInteractionListener,LocationListener{


    private LocationManager manejador;
    private Location localitzacio;
    private int SOLICITUD_PERMISO_LOCALIZACION=0;
    private static final long DOS_MINUTOS = 2 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    private void ultimaLocalizacion(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                actualitzaLocalitzacio(manejador.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            }

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass=null;
        if (id == R.id.llista_llocs) {
            fragmentClass = LlistaLlocs.class;
        } else if (id == R.id.afegir_lloc) {
            fragmentClass = AfegirLloc.class;

        } else if (id == R.id.mapa) {
            fragmentClass = MapsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public static void solicitarPermis(final String permis, String justificacio,
                                       final int requestCode, final Activity activitat) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activitat,
                permis)){
            new AlertDialog.Builder(activitat)
                    .setTitle("Solicitut de permís")
                    .setMessage(justificacio)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(activitat,
                                    new String[]{permis}, requestCode);
                        }})
                    .show();
        } else {
            ActivityCompat.requestPermissions(activitat,
                    new String[]{permis}, requestCode);
        }
    }

    private void activarProveidors() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.
                ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                manejador.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        20 * 1000, 5, (LocationListener) this);
            }
            if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        10 * 1000, 10, (LocationListener) this);
            }
        } else {
            solicitarPermis(Manifest.permission.ACCESS_FINE_LOCATION,
                    "Sense el permís de localització no puc mostrar la distància als llocs.",
                    SOLICITUD_PERMISO_LOCALIZACION, this);
        }
    }


    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == SOLICITUD_PERMISO_LOCALIZACION) {
            if (grantResults.length== 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ultimaLocalizacion();
                activarProveidors();
                //adaptador.notifyDataSetChanged();
            }
        }
    }

    private void actualitzaLocalitzacio(Location location) {
        if (location != null && (localitzacio == null
                || location.getAccuracy() < 2*localitzacio.getAccuracy()
                || location.getTime() - localitzacio.getTime() > DOS_MINUTOS)) {
            //Log.d(Lugares.TAG, "Nueva mejor localización");
            localitzacio = location;
            //Lugares.posicionActual.setLatitud(localiz.getLatitude());
            //Lugares.posicionActual.setLongitud(localiz.getLongitude());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activarProveidors();

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            manejador.removeUpdates((LocationListener) this);
        }

    }

    @Override
    public void onLocationChanged(Location location){
        //Log.d(Lugares.TAG, "Nueva localización: "+location);
        actualitzaLocalitzacio(location);
        //adaptador.notifyDataSetChanged();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //Log.d(Lugares.TAG, "Cambia estado: "+provider);
        activarProveidors();

    }

    @Override
    public void onProviderEnabled(String provider) {
        //Log.d(Lugares.TAG, "Se habilita: "+provider);
        activarProveidors();

    }

    @Override
    public void onProviderDisabled(String provider) {
        //Log.d(Lugares.TAG, "Se deshabilita: "+provider);
        activarProveidors();

    }

    /*public double distancia(GeoPunto punto) {
        final double RADIO_TIERRA = 6371000; // en metros
        double dLat = Math.toRadians(latitud - punto.latitud);
        double dLon = Math.toRadians(longitud - punto.longitud);
        double lat1 = Math.toRadians(punto.latitud);
        double lat2 = Math.toRadians(latitud);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) *
                        Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return c * RADIO_TIERRA;
    }*/

}
