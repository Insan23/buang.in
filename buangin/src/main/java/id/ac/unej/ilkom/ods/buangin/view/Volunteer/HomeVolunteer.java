package id.ac.unej.ilkom.ods.buangin.view.Volunteer;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.view.HalamanMasuk;
import id.ac.unej.ilkom.ods.buangin.view.Pengaturan;
import id.ac.unej.ilkom.ods.buangin.view.Riwayatku;
import id.ac.unej.ilkom.ods.buangin.view.UmpanBalik;

public class HomeVolunteer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener /*, HomeFragment.OnMenuClickListener*/ {

    public static String ACTIVE = "fragment_volunteer_aktif";
    HomeFragment homeFragment;
    SampahkuFragment sampahkuFragment;
    BankSampahFragment bankSampahFragment;
    InfoPoinFragment infoPoinFragment;
    private BottomNavigationView nav_volunteer;
    private NavigationView navigationView;
    private ImageButton buka_kamera;
    private Pengaturan f_nav_pengaturan;
    private Riwayatku f_nav_riwayat;
    private UmpanBalik f_nav_timbalBalik;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_volunteer);
        setSupportActionBar(toolbar);

        homeFragment = new HomeFragment();
        sampahkuFragment = new SampahkuFragment();
        infoPoinFragment = new InfoPoinFragment();
        bankSampahFragment = new BankSampahFragment();
        auth = FirebaseAuth.getInstance();

        f_nav_riwayat = new Riwayatku();
        f_nav_pengaturan = new Pengaturan();
        f_nav_timbalBalik = new UmpanBalik();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        fm.beginTransaction().replace(R.id.container_volunteer, homeFragment, ACTIVE).commit();

        nav_volunteer = (BottomNavigationView) findViewById(R.id.nav_volunteer);
        nav_volunteer.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_vol_home:
                        fm.beginTransaction().replace(R.id.container_volunteer, homeFragment, ACTIVE).commit();
                        break;
                    case R.id.nav_vol_sampah:
                        fm.beginTransaction().replace(R.id.container_volunteer, sampahkuFragment, ACTIVE).commit();
                        break;
                    case R.id.nav_vol_poin:
                        fm.beginTransaction().replace(R.id.container_volunteer, infoPoinFragment, ACTIVE).commit();
                        break;
                    case R.id.nav_vol_bank:
                        fm.beginTransaction().replace(R.id.container_volunteer, bankSampahFragment, ACTIVE).commit();
                        break;
                }
                return true;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_volunteer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_side_volunteer);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_timbalBalik) {
//            fm.beginTransaction().replace(R.id.container_volunteer, new UmpanBalik()).commit();
//        } else if (id == R.id.nav_riwayat) {
//            fm.beginTransaction().replace(R.id.container_volunteer, new Riwayatku()).commit();
//        } else
//        if (id == R.id.nav_pengaturan) {
//            fm.beginTransaction().replace(R.id.container_volunteer, new Pengaturan()).commit();
//        } else
        if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_volunteer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_volunteer);
        drawer.closeDrawer(GravityCompat.START);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                HomeVolunteer.this.finish();
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
}
