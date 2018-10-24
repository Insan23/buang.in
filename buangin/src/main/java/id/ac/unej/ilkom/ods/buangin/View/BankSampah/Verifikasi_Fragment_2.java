package id.ac.unej.ilkom.ods.buangin.View.BankSampah;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Adapter.Verifikasi_Adapter;
import id.ac.unej.ilkom.ods.buangin.Model.Verifikasi;
import id.ac.unej.ilkom.ods.buangin.Model.bs_verifikasi_model_2;
import id.ac.unej.ilkom.ods.buangin.R;

public class Verifikasi_Fragment_2 extends Fragment {

    private ImageButton cek_kode, hapus_kode;
    private EditText kode_sampah;
    private TextView nama, level;

    private DatabaseReference databaseReference, dRef, reference;
    private FirebaseDatabase firebaseDatabase, fDat, database;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private bs_verifikasi_model_2 verifikasi_model;

    private Verifikasi_Adapter adapter;
    private List<Verifikasi> list;
    private RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank_sampah_verifikasi_2, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_verifikasi_2);

        nama = (TextView) view.findViewById(R.id.text_user_bank_sampah);
        level = (TextView) view.findViewById(R.id.text_level_bank_sampah);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String UID = firebaseUser.getUid();

        fDat = FirebaseDatabase.getInstance();
        dRef = fDat.getReference("pengguna").child(UID);
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    bs_verifikasi_model_2 verifikasiModel2 = dataSnapshot1.getValue(bs_verifikasi_model_2.class);
                    String stringNama = verifikasiModel2.getNama();
                    String stringLevel = verifikasiModel2.getLevel();

                    nama.setText(stringNama);
                    level.setText(stringLevel);
                    System.out.println("sampah verifikasi nama : " + stringNama);
                    System.out.println("sampah verifikasi level : " + stringLevel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cek_kode = (ImageButton) view.findViewById(R.id.btn_cek_kode_2);
        hapus_kode = (ImageButton) view.findViewById(R.id.btn_hapus_kode);
        kode_sampah = (EditText) view.findViewById(R.id.input_kode_sampah);

        hapus_kode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kode_sampah.setText("");
            }
        });

        cek_kode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stringKode = kode_sampah.getText().toString().trim();
                if (stringKode.isEmpty()) {
                    kode_sampah.requestFocus();
                    kode_sampah.setError("Isikan kodenya terlebih dahulu!");
                } else {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("dataSampah").child(stringKode);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                DialogVerifikasi dialogVerifikasi = new DialogVerifikasi();
                                dialogVerifikasi.setKode(stringKode);
                                dialogVerifikasi.show(getChildFragmentManager(), "verifikasi");
                                System.out.println("data snapshot : " + dataSnapshot.toString());
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                        .setCancelable(false)
                                        .setTitle("buang.in")
                                        .setMessage("Data sampah tidak tersedia, mohon cek ulang kodemu!")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        adapter = new Verifikasi_Adapter(list, getActivity());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("dataSampah");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Verifikasi>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Verifikasi verifikasi = dataSnapshot1.getValue(Verifikasi.class);
                    Verifikasi model = new Verifikasi();
                    String kode = verifikasi.getKodeSampah();
                    String submit = verifikasi.getTanggalSubmit();
                    String poin = verifikasi.getPoinSampah();
                    String harga = verifikasi.getHargaSampah();
                    String status = verifikasi.getStatusVerifikasi();
                    String berat = verifikasi.getBeratSampah();
                    String jenis = verifikasi.getJenisSampah();
//                    model.setBeratSampah(berat);
//                    model.setHargaSampah(harga);
//                    model.setJenisSampah(jenis);
//                    model.setKodeSampah(kode);
//                    model.setPoinSampah(poin);
//                    model.setStatusVerifikasi(status);
//                    model.setTanggalSubmit(submit);

                    Verifikasi verifikasi1_model = new Verifikasi(kode, null, null, jenis, poin + " poin", submit, "Rp. " + harga, berat + " Kg", status);
                    list.add(verifikasi1_model);

                    System.out.println("verifikasi kode : " + kode);
                    System.out.println("verifikasi submit : " + submit);
                    System.out.println("verifikasi poin : " + poin);
                    System.out.println("verifikasi harga : " + harga);
                    System.out.println("verifikasi status : " + status);
                    System.out.println("verifikasi berat : " + berat);
                    System.out.println("verifikasi jenis : " + jenis);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Hello", "Database error : ", databaseError.toException());
            }
        });
        return view;
    }
}
