package com.battistradadeveloper.smartfarmoffline.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.battistradadeveloper.smartfarmoffline.R;
import com.battistradadeveloper.smartfarmoffline.model.DataModel;

import java.util.List;

public class RecycleAdapter extends ArrayAdapter<DataModel> {

    Context mCtx;
    int listLayoutres;
    List<DataModel> dataModelList;
    SQLiteDatabase mDatabase;
    TextView txtNameBeras, txtTypeBeras, txtYearBeras, txtDescriptionBeras,
    txtPriceBeras, txtOwnerBeras, txtOwnerAddress;

    public RecycleAdapter(Context mCtx, int listLayoutres, List<DataModel> dataModelList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutres, dataModelList);

        this.mCtx = mCtx;
        this.listLayoutres = listLayoutres;
        this.dataModelList = dataModelList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutres, null);

        final DataModel dataModel = dataModelList.get(position);

        //Menghubungkan variabel java dengan id xml yang ada pada itemview
        txtNameBeras = view.findViewById(R.id.name1);
        txtTypeBeras = view.findViewById(R.id.type1);
        txtYearBeras = view.findViewById(R.id.year1);
        txtDescriptionBeras = view.findViewById(R.id.desc1);
        txtPriceBeras = view.findViewById(R.id.price1);
        txtOwnerBeras = view.findViewById(R.id.owner1);
        txtOwnerAddress = view.findViewById(R.id.owneradd1);

        //Mengambil nilai yang ada pada SQLite
        txtNameBeras.setText(dataModel.getName());
        txtTypeBeras.setText(dataModel.getType());
        txtYearBeras.setText(dataModel.getYear());
        txtDescriptionBeras.setText(dataModel.getDescription());
        txtPriceBeras.setText(String.valueOf(dataModel.getPrice()));
        txtOwnerBeras.setText(dataModel.getOwner());
        txtOwnerAddress.setText(dataModel.getOwneradd());

        Button btndelete = view.findViewById(R.id.btn_delete);
        Button btnEdit = view.findViewById(R.id.btn_edit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatamodel(dataModel);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM datamodel WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{dataModel.getId()});
                        reloadDataModelsFromDatabase();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }

    private void updateDatamodel(final DataModel dataModel){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_datamodel,null);
        builder.setView(view);
        final EditText edtName = view.findViewById(R.id.edt_input_nama);
        final Spinner s_type = view.findViewById(R.id.spinnerType);
        final Spinner s_year = view.findViewById(R.id.spinnerYear);
        final EditText edtDescription = view.findViewById(R.id.edt_input_deskripsi);
        final EditText edtPrice = view.findViewById(R.id.edt_input_harga);
        final EditText edtOwner = view.findViewById(R.id.edt_input_nama_penjual);
        final EditText edtOwnerAddress = view.findViewById(R.id.edt_input_alamat_penjual);

        edtName.setText(dataModel.getName());
        edtDescription.setText(dataModel.getDescription());
        edtPrice.setText(String.valueOf(dataModel.getPrice()));
        edtOwner.setText(dataModel.getOwner());
        edtOwnerAddress.setText(dataModel.getOwneradd());

        final AlertDialog dialog = builder.create();
        dialog.show();
        view.findViewById(R.id.buttonUpdateBeras).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String type = s_type.getSelectedItem().toString();
                String year = s_year.getSelectedItem().toString();
                String desc = edtDescription.getText().toString().trim();
                String price = edtPrice.getText().toString().trim();
                String owner = edtOwner.getText().toString().trim();
                String owneradd = edtOwnerAddress.getText().toString().trim();

                if (name.isEmpty()){
                    edtName.setError("Name can't be blank");
                    edtName.requestFocus();
                    return;
                }
                if (desc.isEmpty()){
                    edtDescription.setError("Description can't be empty");
                    edtDescription.requestFocus();
                    return;
                }
                if (price.isEmpty()){
                    edtPrice.setError("Price can't be empty");
                    edtPrice.requestFocus();
                    return;
                }
                if (owner.isEmpty()){
                    edtOwner.setError("Owner can't be empty");
                    edtOwner.requestFocus();
                    return;
                }
                if (owneradd.isEmpty()){
                    edtOwnerAddress.setError("Owner Address can't be empty");
                    edtOwnerAddress.requestFocus();
                    return;
                }

                String sql = "UPDATE datamodels \n" +
                        "SET name = ?, \n" +
                        "type = ?, \n" +
                        "year = ?, \n" +
                        "description = ?, \n " +
                        "price = ?, \n" +
                        "owner = ?, \n" +
                        "owneradd = ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{name, type, year, desc, price, owner, owneradd, String.valueOf(dataModel.getId())});
                Toast.makeText(mCtx, "DataModel Updated", Toast.LENGTH_LONG).show();
                reloadDataModelsFromDatabase();
                dialog.dismiss();
            }
        });
    }

    private void reloadDataModelsFromDatabase(){
        Cursor cursorDataModels = mDatabase.rawQuery("SELECT * FROM DataModels", null);
        if (cursorDataModels.moveToFirst()){
            dataModelList.clear();
            do {
                dataModelList.add(new DataModel(
                        cursorDataModels.getInt(0),
                        cursorDataModels.getString(1),
                        cursorDataModels.getString(2),
                        cursorDataModels.getString(3),
                        cursorDataModels.getString(4),
                        cursorDataModels.getString(5),
                        cursorDataModels.getString(6),
                        cursorDataModels.getDouble(7)
                ));
            }while (cursorDataModels.moveToNext());
        }
        cursorDataModels.close();
        notifyDataSetChanged();
    }
}
