package id.ac.unej.ilkom.ods.buangin.view.Perusahaan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.view.HalamanMasuk;

public class HomePerusahaan extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    FragmentTransaction ft;
    FragmentManager fm;

    UbahHargaFragment hargaFragment;
    HomeFragment homeFragment;
    TabDaftarHargaFragment daftarHargaFragment;
    TabUbahHargaFragment ubahHargaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perusahaan);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hargaFragment = new UbahHargaFragment();
        homeFragment = new HomeFragment();
        daftarHargaFragment = new TabDaftarHargaFragment();
        ubahHargaFragment = new TabUbahHargaFragment();

        fm.beginTransaction().replace(R.id.container_perusahaan, homeFragment).commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_perusahaan);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_bawah_ubah_harga_perusahaan:
                        fm.beginTransaction().replace(R.id.container_perusahaan, hargaFragment).commit();
                        break;
                    case R.id.nav_bawah_home_perusahaan:
                        fm.beginTransaction().replace(R.id.container_perusahaan, homeFragment).commit();
                        break;
                }
                return true;
            }
        });
    }

//    public void setActionBarTitle(String title){
//        getSupportActionBar().setTitle(title);
//    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                HomePerusahaan.this.finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public TabDaftarHargaFragment getDaftarHargaFragment() {
        return daftarHargaFragment;
    }

    public TabUbahHargaFragment getUbahHargaFragment() {
        return ubahHargaFragment;
    }
}
