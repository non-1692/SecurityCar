package com.securitycar.seguimiento;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.securitycar.R;
import com.securitycar.app.MydataUnidades;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class SeguimientoFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback {

    View rootView;

    private MydataUnidades listUnidades;
    private GoogleMap mapa;
    private MapView mMapView;

    private double lat, lon;
    private String Eco = "", Placas = "", Vel = "", Fecha = "", Hora = "", Status = "", Dir = "";


    private List<LatLng> list;
    private Marker markers;

    private boolean init;

    private TextView tvEco, tvPlacas, tvFyH, tvStatus, tvVel, tvDir;

    public SeguimientoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_seguimiento, container, false);

        //listUnidades = getArguments() != null ? getArguments().getParcelable("item") : null;
        listUnidades = getArguments().getParcelable("item");


        Eco = listUnidades.getEco();
        Placas = listUnidades.getPlacas();
        Vel = listUnidades.getVel();
        Fecha = listUnidades.getFecha();
        Hora = listUnidades.getHora();
        Status = listUnidades.getStatusMotor();
        Dir = listUnidades.getDir();

        mMapView = (MapView) rootView.findViewById(R.id.mapView);

        tvEco = (TextView) rootView.findViewById(R.id.tvEco);
        tvPlacas = (TextView) rootView.findViewById(R.id.tvPlacas);
        tvFyH = (TextView) rootView.findViewById(R.id.tvFyH);
        tvStatus = (TextView) rootView.findViewById(R.id.tvStatus);
        tvVel = (TextView) rootView.findViewById(R.id.tvVel);
        tvDir = (TextView) rootView.findViewById(R.id.tvDir);

        list = new ArrayList<>();

        lat = listUnidades.getLatitud();
        lon = listUnidades.getLongitud();


        LatLng coordenadas = new LatLng(lat, lon);
        list.add(coordenadas);
        init = true;

        MapsInitializer.initialize(getContext());
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        Log.d("SeguimientoF", "Eco" + listUnidades.getEco());
        Log.d("SeguimientoF", "Placas" + listUnidades.getPlacas());

        cambioStatus();

        return rootView;
    }

    private void cambioStatus() {
        tvEco.setText(Eco);
        tvPlacas.setText("Placas: "+Placas);
        tvFyH.setText(Fecha);
        tvStatus.setText(Status);
        tvVel.setText("Vel: "+Vel+" Km/h");
        tvDir.setText(Dir);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapa = googleMap;

        mapa.getUiSettings().setMapToolbarEnabled(false); // Utilidad Google Maps (Como llegar)
        mapa.getUiSettings().setZoomControlsEnabled(true); // Control Zoom

        mapa.setOnMapLoadedCallback(this);

    }

    @Override
    public void onMapLoaded() {
        dibujaMapa();
    }

    private void dibujaMapa() {

        LatLng coordenadas = new LatLng(lat, lon);
        markers = mapa.addMarker(new MarkerOptions()
                        .position(coordenadas)
                        .title(Eco)
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
        );

        if (list.size() > 0) {
            Polyline line = mapa.addPolyline(new PolylineOptions()
                    .addAll(list)
                    .width(20)
                    .color(getResources().getColor(R.color.Bgpoligono))
                    .geodesic(true)
            );
        }
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(coordenadas, 20);
        if (init) {
            mapa.moveCamera(cameraUpdate);
        } else {
            mapa.animateCamera(cameraUpdate);
        }
        init = false;
    }

    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
