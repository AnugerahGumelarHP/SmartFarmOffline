package com.battistradadeveloper.smartfarmoffline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.battistradadeveloper.smartfarmoffline.R;
import com.battistradadeveloper.smartfarmoffline.helper.DatabaseHelper;
import com.battistradadeveloper.smartfarmoffline.model.DataModel;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Myholder> {
    DatabaseHelper databaseHelper;
    List<DataModel> dataModelArrayList;

    public RecycleAdapter(List<DataModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder{
        TextView name,type,year, desc, owner,owneradd;

        public Myholder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name1);
            type = itemView.findViewById(R.id.type1);
            year = itemView.findViewById(R.id.year1);
            desc = itemView.findViewById(R.id.desc1);
            owner = itemView.findViewById(R.id.owner1);
            owneradd = itemView.findViewById(R.id.owneradd1);
        }
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,null);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        DataModel dataModel=dataModelArrayList.get(position);
        holder.name.setText(dataModel.getName());
        holder.type.setText(dataModel.getType());
        holder.year.setText(dataModel.getYear());
        holder.desc.setText(dataModel.getDescrip());
        holder.owner.setText(dataModel.getOwner());
        holder.owner.setText(dataModel.getOwneradd());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}
