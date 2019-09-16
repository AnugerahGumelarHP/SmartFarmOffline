package com.battistradadeveloper.smartfarmoffline.fragment_admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.battistradadeveloper.smartfarmoffline.R;
import com.battistradadeveloper.smartfarmoffline.adapter.RecycleAdapter;
import com.battistradadeveloper.smartfarmoffline.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class HomeAdmin extends Fragment{
    List<DataModel> dataModelList;
    SQLiteDatabase mDatabase;
    ListView listViewDataModels;
    RecycleAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_admin_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    	listViewDataModels = view.findViewById(R.id.recycle);
    	dataModelList = new ArrayList<>();

    	//opening the database
        mDatabase = SQLiteDatabase.openOrCreateDatabase(InputAdmin.DATABASE_NAME, null);

	    //this method will display
        showBerassFromDatabase();
    }

    private void showBerassFromDatabase(){
        //we used rawQuery(sql, selectionargs) for fetching all the berass
        Cursor cursorBerass = mDatabase.rawQuery("SELECT * FROM berass", null);

        //if the cursor has some data
        if (cursorBerass.moveToFirst()){
            //loopinh through all the records
            do {
                //pushing each record in the beras list
                dataModelList.add(new DataModel(
                        cursorBerass.getInt(0),
                        cursorBerass.getString(1),
                        cursorBerass.getString(2),
                        cursorBerass.getString(3),
                        cursorBerass.getString(4),
                        cursorBerass.getString(5),
                        cursorBerass.getString(6),
                        cursorBerass.getDouble(7)
                ));
            } while (cursorBerass.moveToNext());
        }
        //closing the cursor
        cursorBerass.close();

        //creating the adapter object
        adapter = new RecycleAdapter(getActivity(), R.layout.itemview, dataModelList, mDatabase);

        //adding the adapter to listview
        listViewDataModels.setAdapter(adapter);
    }


}
