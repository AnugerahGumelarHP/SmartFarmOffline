package com.battistradadeveloper.smartfarmoffline.fragment_admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.battistradadeveloper.smartfarmoffline.R;
import com.battistradadeveloper.smartfarmoffline.fragment.Login;
import com.battistradadeveloper.smartfarmoffline.home.MainActivity;
import com.battistradadeveloper.smartfarmoffline.home.MainAdmin;

public class ProfileAdmin extends Fragment {
    TextView txt_profile_nama, txt_profile_nim, txt_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);

        return view;
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau          // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        txt_profile_nama = view.findViewById(R.id.txt_profile_name);
        txt_profile_nim = view.findViewById(R.id.txt_profile_nim);


        txt_logout = view.findViewById(R.id.fitur_profile_logout);
        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Apakah anda yakin ingin keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Yakin", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent logoutproses = new Intent(getActivity(), MainActivity.class);
                                startActivity(logoutproses);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
