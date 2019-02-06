package com.example.application_jxf.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application_jxf.R;
import com.example.application_jxf.adapters.UserReservesAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    private List<String> list;
    private View view;
    private UserReservesAdapter adapter;
    private RecyclerView recyclerView;

    public static UserFragment newInstance(List<String> data){
        UserFragment fragment = new UserFragment();
        fragment.list = data;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        Log.e("onCreateView: ", list.toString());
        adapter = new UserReservesAdapter(list, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }


}
