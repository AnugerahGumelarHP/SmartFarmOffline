package com.battistradadeveloper.smartfarmoffline.fragment_admin;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.battistradadeveloper.smartfarmoffline.R;
import com.battistradadeveloper.smartfarmoffline.model.DataModel;

public class InputAdmin extends Fragment {
    public static final String DATABASE_NAME = "myberasdatabase";

    TextView txtViewViewBeras,
            txt_admin_info, txt_admin_beras, txt_admin_jenis,
            txt_admin_tahun, txt_admin_deskripsi, txt_admin_harga,
            txt_admin_penjual, txt_admin_alamat, submitdata;
    EditText edt_admin_beras, edt_admin_deskripsi, edt_admin_harga,
            edt_admin_penjual, edt_admin_alamat;
    Spinner s_type, s_year;

    SQLiteDatabase mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_input, container, false);

        return view;
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    //  Tulisan info di bagian atas
        txt_admin_info = view.findViewById(R.id.txt_input_info);

        txt_admin_beras = view.findViewById(R.id.txt_input_nama);
        txt_admin_jenis = view.findViewById(R.id.txt_input_jenis);
        txt_admin_tahun = view.findViewById(R.id.txt_input_tahun);
        txt_admin_deskripsi = view.findViewById(R.id.txt_input_deskripsi);
        txt_admin_penjual = view.findViewById(R.id.txt_input_nama_penjual);
        txt_admin_alamat = view.findViewById(R.id.txt_input_alamat_penjual);
        txt_admin_harga = view.findViewById(R.id.txt_input_harga);

        edt_admin_beras = view.findViewById(R.id.edt_input_nama);
        edt_admin_deskripsi = view.findViewById(R.id.edt_input_deskripsi);
        edt_admin_penjual = view.findViewById(R.id.edt_input_nama_penjual);
        edt_admin_alamat = view.findViewById(R.id.edt_input_alamat_penjual);
        edt_admin_harga = view.findViewById(R.id.edt_input_harga);

        s_type = view.findViewById(R.id.spinnerType);
        s_year = view.findViewById(R.id.spinnerYear);

        submitdata = view.findViewById(R.id.fitur_input_simpan);
        submitdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBerass();
            }
        });

        mDatabase = SQLiteDatabase.openOrCreateDatabase(DATABASE_NAME, null);
        createBerasTable();
    }

    private void createBerasTable(){
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS berass(" +
                " id INTEGER NOT NULL CONSTRAINT beras_pk PRIMARY KEY AUTOINCREMENT,\n" +
                " name varchar(200) NOT NULL, \n" +
                " s_type varchar(200) NOT NULL, \n" +
                " s_year varchar(200) NOT NULL, \n" +
                " price double NOT NULL, \n" +
                " description varchar(200) NOT NULL, \n" +
                " owner varchar(50) NOT NULL, \n" +
                " owneradd varchar(200) NOT NULL\n" +
                ");"
        );
    }

    private boolean inputsAreCorrect(String name, String price, String description,
                                     String owner, String owneraddress){
        if (name.isEmpty()){
            edt_admin_beras.setError("Please enter a name");
            edt_admin_beras.requestFocus();
            return false;
        }
        if (price.isEmpty() || Integer.parseInt(price) <= 0){
            edt_admin_harga.setError("Please enter price");
            edt_admin_harga.requestFocus();
            return false;
        }
        if (description.isEmpty()){
            edt_admin_deskripsi.setError("Plese enter description");
            edt_admin_deskripsi.requestFocus();
            return false;
        }
        if (owner.isEmpty()){
            edt_admin_penjual.setError("Please enter owner");
            edt_admin_penjual.requestFocus();
            return false;
        }
        if (owneraddress.isEmpty()){
            edt_admin_alamat.setError("Please enter owner address");
            edt_admin_alamat.requestFocus();
            return false;
        }
        return true;
    }

    //In this method we will do the create operation
    private void addBerass(){
        String name = edt_admin_beras.getText().toString().trim();
        String type = s_type.getSelectedItem().toString().trim();
        String year = s_year.getSelectedItem().toString().trim();
        String price = edt_admin_harga.getText().toString().trim();
        String description = edt_admin_deskripsi.getText().toString().trim();
        String owner = edt_admin_penjual.getText().toString().trim();
        String owneraddress = edt_admin_alamat.getText().toString().trim();

        //validating the inputs
        if (inputsAreCorrect(name, price, description, owner, owneraddress)){
            String insertSQL = "INSERT INTO berass \n" +
                    "(name, type, year, price, description, owner, owneraddress)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?, ?, ?, ?);";

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that us to be binded with the query

            mDatabase.execSQL(insertSQL, new String[]{name, type, year, price, description, owner, owneraddress});

            Toast.makeText(getActivity(), "Beras Added Successfully", Toast.LENGTH_LONG).show();
        }
    }


}
