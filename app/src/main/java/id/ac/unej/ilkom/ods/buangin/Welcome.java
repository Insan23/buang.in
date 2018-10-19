package id.ac.unej.ilkom.ods.buangin;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unej.ilkom.ods.buangin.Model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.View.BankSampah.HomeBankSampah;
import id.ac.unej.ilkom.ods.buangin.View.HalamanMasuk;
import id.ac.unej.ilkom.ods.buangin.View.Mitra.HomeMitra;
import id.ac.unej.ilkom.ods.buangin.View.Perusahaan.HomePerusahaan;
import id.ac.unej.ilkom.ods.buangin.View.Volunteer.HomeVolunteer;

import static id.ac.unej.ilkom.ods.buangin.Util.Util.WRITE_EXTERNAL;

public class Welcome extends AppCompatActivity {

    /**
     * TODO tukar poin volunteer (ke produk mitra)
     * TODO riwayat penukaran voucher di volunteer
     * TODO verifikasi voucher di volunteer
     * TODO tambah produk mitra
     * TODO pencarian ID/Kode sampah milik volunteer di bank sampah
     * TODO riwayat penukaran voucher di mitra
     * TODO jumlah voucher berkurang di mitra
     * TODO fitur perusahaan request sampah kepada bank sampah dengan harga tersendiri
     * TODO verifikasi per sampah di bank sampah (memilah sampah)
     *
     */

    private static final String TAG = "Welcome_Page";

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressBar prog = findViewById(R.id.loading_welcome);
                prog.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("id.ac.unej.ilkom.ods.buangin", 0);
                        if (pref.getBoolean("first_launch", true)) {
                            pref.edit().putBoolean("first_lauch", false).commit();
                            startActivity(new Intent(Welcome.this, HalamanAwal.class));
                        } else {
                            if (user != null) {
                                dbRef.child("pengguna").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                                            Pengguna pen = child.getValue(Pengguna.class);
                                            cekLevel(pen.getLevel());
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(Welcome.this, "Gagal Login / Sesi Berakhir", Toast.LENGTH_LONG).show();
                                        Log.w(TAG, "Login ERROR : " + databaseError.getDetails());
                                        startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                                    }
                                });
                            } else {
                                startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                            }
                        }
                    }
                }, 2000);
            }
        }, 1000);
    }


    @Override
    public void onBackPressed() {

    }

    private void cekLevel(String level) {
        switch (level) {
            case Pengguna.VOLUNTEER:
                startActivity(new Intent(Welcome.this, HomeVolunteer.class));
                break;
            case Pengguna.BANK_SAMPAH:
                startActivity(new Intent(Welcome.this, HomeBankSampah.class));
                break;
            case Pengguna.MITRA:
                startActivity(new Intent(Welcome.this, HomeMitra.class));
                break;
            case Pengguna.PERUSAHAAN:
                startActivity(new Intent(Welcome.this, HomePerusahaan.class));
                break;
            default:
                Toast.makeText(Welcome.this, "Tidak Diketahui", Toast.LENGTH_LONG).show();
                Log.w(TAG, "Level Tidak Diketahui");
        }
//        View context = findViewById(R.id.root_layout_masuk);
//        Snackbar.make(context, "Masuk Sebagai " + level, Snackbar.LENGTH_LONG).show();
        Log.d(TAG, "Berhasil Masuk");
    }
}
