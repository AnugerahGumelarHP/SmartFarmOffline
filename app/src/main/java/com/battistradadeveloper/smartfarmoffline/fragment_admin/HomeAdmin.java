package com.battistradadeveloper.smartfarmoffline.fragment_admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.battistradadeveloper.smartfarmoffline.R;
import com.battistradadeveloper.smartfarmoffline.adapter.RecycleAdapter;
import com.battistradadeveloper.smartfarmoffline.helper.DatabaseHelper;
import com.battistradadeveloper.smartfarmoffline.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class HomeAdmin extends Fragment{
    DatabaseHelper database;
    RecyclerView recyclerView;
    RecycleAdapter recycler;
    List<DataModel> datamodel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_admin_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        datamodel = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycle);

        database = new DatabaseHelper(getActivity());
        datamodel = database.getdata();

        recycler = new RecycleAdapter(datamodel);

        Log.i("HIteshdata",""+datamodel);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);


    }


}
