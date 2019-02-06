package com.example.application_jxf.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application_jxf.R;

public class MapFragment extends Fragment {
    private String data;
    private View view;

    public static MapFragment newInstance(String data){
        MapFragment fragment = new MapFragment();
        fragment.data = data;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

}
