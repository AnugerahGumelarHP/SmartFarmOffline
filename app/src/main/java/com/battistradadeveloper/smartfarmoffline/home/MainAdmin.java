package com.battistradadeveloper.smartfarmoffline.home;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.battistradadeveloper.smartfarmoffline.R;
import com.battistradadeveloper.smartfarmoffline.fragment.FarmRice;
import com.battistradadeveloper.smartfarmoffline.fragment.Home;
import com.battistradadeveloper.smartfarmoffline.fragment.Login;
import com.battistradadeveloper.smartfarmoffline.fragment_admin.HomeAdmin;
import com.battistradadeveloper.smartfarmoffline.fragment_admin.InputAdmin;
import com.battistradadeveloper.smartfarmoffline.fragment_admin.ProfileAdmin;

public class MainAdmin extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        // kita set default nya HomeAdmin Fragment
        loadFragment(new HomeAdmin());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main_admin);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_admin, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragmentadmin = null;
        switch (menuItem.getItemId()){
            case R.id.homeadmin:
                fragmentadmin = new HomeAdmin();
                break;
            case R.id.inputadmin:
                fragmentadmin = new InputAdmin();
                break;
            case R.id.profileadmin:
                fragmentadmin = new ProfileAdmin();
                break;
        }
        return loadFragment(fragmentadmin);
    }
}
