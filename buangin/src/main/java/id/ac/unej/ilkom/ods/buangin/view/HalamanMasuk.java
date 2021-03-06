package id.ac.unej.ilkom.ods.buangin.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.Welcome;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.view.BankSampah.HomeBankSampah;
import id.ac.unej.ilkom.ods.buangin.view.Mitra.HomeMitra;
import id.ac.unej.ilkom.ods.buangin.view.Perusahaan.HomePerusahaan;
import id.ac.unej.ilkom.ods.buangin.view.Volunteer.HomeVolunteer;

public class HalamanMasuk extends AppCompatActivity {

    private static String TAG = "HalamanMasuk";

    private TextInputEditText email, password;
    private Button masuk;
    private TextView daftar;
    private String strEmail, strPassword;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        auth = FirebaseAuth.getInstance();

        dbRef = FirebaseDatabase.getInstance().getReference();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        email = (TextInputEditText) findViewById(R.id.input_masukEmail);
        password = (TextInputEditText) findViewById(R.id.input_masukPassword);

        masuk = (Button) findViewById(R.id.btn_masuk);
        daftar = (TextView) findViewById(R.id.btn_daftar);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = email.getText().toString();
                strPassword = password.getText().toString();
                boolean bolehMasuk = true;

                String emailValid = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (strEmail.isEmpty()) {
                    email.requestFocus();
                    bolehMasuk = false;
                    email.setError("Isi terlebih dahulu!");
                } else {
                    bolehMasuk = true;
                }
                if (strPassword.isEmpty()) {
                    password.requestFocus();
                    bolehMasuk = false;
                    password.setError("Isi terlebih dahulu!");
                } else {
                    bolehMasuk = true;
                }

                if (bolehMasuk) {
                    if (strEmail.matches(emailValid)) {
                        auth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(HalamanMasuk.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = auth.getCurrentUser();
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
                                            Toast.makeText(HalamanMasuk.this, "Username Atau Email Salah", Toast.LENGTH_LONG).show();
                                            Log.w(TAG, "Login ERROR : " + databaseError.getDetails());
                                        }
                                    });

                                } else {
                                    if (cekJaringan()) {

                                    }
                                    Toast.makeText(HalamanMasuk.this, "Gagal Masuk", Toast.LENGTH_LONG).show();
                                    Log.w(TAG, "Gagal Masuk: " + task.getException());
                                }
                            }
                        });
                    }
                }
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
            }
        });
    }

    private void cekLevel(String level) {
        switch (level) {
            case Pengguna.VOLUNTEER:
                startActivity(new Intent(HalamanMasuk.this, HomeVolunteer.class));
                break;
            case Pengguna.BANK_SAMPAH:
                startActivity(new Intent(HalamanMasuk.this, HomeBankSampah.class));
                break;
            case Pengguna.MITRA:
                startActivity(new Intent(HalamanMasuk.this, HomeMitra.class));
                break;
            case Pengguna.PERUSAHAAN:
                startActivity(new Intent(HalamanMasuk.this, HomePerusahaan.class));
                break;
            default:
                Toast.makeText(HalamanMasuk.this, "Tidak Diketahui", Toast.LENGTH_LONG).show();
                Log.w(TAG, "Level Tidak Diketahui");
        }
        View context = findViewById(R.id.root_layout_masuk);
        Snackbar.make(context, "Masuk Sebagai " + level, Snackbar.LENGTH_LONG).show();
        Log.d(TAG, "Berhasil Masuk");
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Tutup aplikasi BUANG.IN?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                System.exit(1);
                //                HalamanMasuk.this.finish();
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT", true);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean cekJaringan() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
