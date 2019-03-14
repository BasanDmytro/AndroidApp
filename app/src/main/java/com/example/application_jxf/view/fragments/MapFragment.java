package com.example.application_jxf.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.application_jxf.R;
import com.example.application_jxf.pojo.RestourantItem;
import com.example.application_jxf.view.BookingActivity;
import com.example.application_jxf.view.TabActivty;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class MapFragment extends Fragment {

    private List<RestourantItem> list;
    MapView mMapView;
    private GoogleMap googleMap;

    public static MapFragment newInstance(List<RestourantItem> data){
        MapFragment fragment = new MapFragment();
        fragment.list = data;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {



            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                try {

                    googleMap.setMyLocationEnabled(true);
                } catch (SecurityException e){
//                    Log.d(TAG, )
                }

//                // For dropping a marker at a point on the Map
                for (RestourantItem item: list){
                    Marker marker =
                            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(item.getX(),item.getY()))
                            .title(item.getTitle())
                            .snippet(item.getDescription()));
                    marker.setTag(Long.valueOf(item.get_id()));
                }

                // For zooming automatically to the location of the marker
                LatLng sydney = new LatLng(-34, 151);
                if (list != null && !list.isEmpty()){
                    sydney = new LatLng(list.get(0).getX(), list.get(0).getY());
                }
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.getUiSettings().setAllGesturesEnabled(true);
                googleMap.getUiSettings().setMapToolbarEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setCompassEnabled(true);
                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        if (marker.getTag() instanceof Long) {
                            Long l = (Long) marker.getTag();
                            if (l % 2 == 0){
//                                first screen
                                Intent intent = new Intent(getActivity(), BookingActivity.class);
                                startActivity(intent);
//                                Toast.makeText(getActivity(), "001", Toast.LENGTH_SHORT).show();
                            } else {
//                                2nd screen
                                Toast.makeText(getActivity(), "002", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        return rootView;
    }

    @Override
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