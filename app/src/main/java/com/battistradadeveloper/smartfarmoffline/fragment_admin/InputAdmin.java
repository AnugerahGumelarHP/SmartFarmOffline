package com.battistradadeveloper.smartfarmoffline.fragment_admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.battistradadeveloper.smartfarmoffline.R;
import com.battistradadeveloper.smartfarmoffline.model.DataModel;

public class InputAdmin extends Fragment {
    TextView txt_admin_info, txt_admin_beras, txt_admin_jenis,
            txt_admin_tahun, txt_admin_deskripsi, txt_admin_harga,
            txt_admin_penjual, txt_admin_alamat, submitdata;
    EditText edt_admin_beras, edt_admin_jenis, edt_admin_tahun,
            edt_admin_deskripsi, edt_admin_harga,
            edt_admin_penjual, edt_admin_alamat;
    String name,type,year,descrip,price,owner,owneraddress;
    RadioGroup rad_year, rad_type;
    RadioButton rad_putih, rad_merah, rad_hitam, rad_before_2019, rad_2019;
    DatabaseHelper databaseHelper;

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
        databaseHelper = new DatabaseHelper(getActivity());

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

        // TODO RadioGroup has no action
        rad_type = view.findViewById(R.id.rad_jenis);
        rad_year = view.findViewById(R.id.rad_tahun);

        //RadioButton for saving data
        rad_before_2019 = view.findViewById(R.id.rad_before_2019);
        rad_2019 = view.findViewById(R.id.rad_2019);
        rad_putih = view.findViewById(R.id.rad_putih);
        rad_merah = view.findViewById(R.id.rad_merah);
        rad_hitam = view.findViewById(R.id.rad_hitam);

        submitdata = view.findViewById(R.id.fitur_input_simpan);
        submitdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt_admin_beras.getText().toString();

                String type = "";
                if (rad_putih.isChecked()){
                    type = "Beras Putih";
                } else if(rad_merah.isChecked()){
                    type = "Beras Merah";
                } else {
                    type = "Beras Hitam";
                }

                String year = "";
                if (rad_before_2019.isChecked()){
                    year = "2019";
                } else {
                    year = "Sebelum 2019";
                }

                descrip = edt_admin_deskripsi.getText().toString();
                owner = edt_admin_penjual.getText().toString();
                owneraddress = edt_admin_alamat.getText().toString();
                price = edt_admin_harga.getText().toString();

                //Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                if (name.isEmpty() && type.isEmpty()&& year.isEmpty()&& descrip.isEmpty()&& price.isEmpty() && owner.isEmpty()&& owneraddress.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Data yang anda input belum lengkap")
                            .setNegativeButton("Retry",null).create().show();
                }else {
                     databaseHelper.insertdata(new DataModel(name,type,year,descrip,owner,owneraddress,price));
                     edt_admin_beras.setText("");
                     edt_admin_jenis.setText("");
                     edt_admin_tahun.setText("");
                     edt_admin_deskripsi.setText("");
                     edt_admin_penjual.setText("");
                     edt_admin_alamat.setText("");
                     edt_admin_harga.setText("");

                }
            }
        });
    }

}
