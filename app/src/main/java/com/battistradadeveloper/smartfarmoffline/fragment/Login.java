package com.battistradadeveloper.smartfarmoffline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.battistradadeveloper.smartfarmoffline.home.MainAdmin;
import com.battistradadeveloper.smartfarmoffline.R;

public class Login extends Fragment {
    EditText edt_email, edt_password;
    TextView txt_email, txt_password, txt_login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau          // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        txt_email = view.findViewById(R.id.txt_login_email);
        txt_password = view.findViewById(R.id.txt_login_password);

        edt_email = view.findViewById(R.id.edt_login_email);
        edt_password = view.findViewById(R.id.edt_login_password);

        txt_login = view.findViewById(R.id.fitur_login);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameKey = edt_email.getText().toString();
                String passwordKey = edt_password.getText().toString();

                if (usernameKey.equals("admin@gmail.com") && passwordKey.equals("admin123")){
                    //jika login berhasil
                    Intent login = new Intent(getActivity(), MainAdmin.class);
                    startActivity(login);
                }
                else{
                    //Jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Username atau Password Anda Salah!")
                            .setNegativeButton("Retry",null).create().show();
                }
            }
        });
    }
}
