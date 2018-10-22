package id.ac.unej.ilkom.ods.buangin.View.BankSampah;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.ac.unej.ilkom.ods.buangin.Model.Verifikasi;
import id.ac.unej.ilkom.ods.buangin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogVerifikasi extends DialogFragment {

    final static String DATA_DITERIMA = "data_diterima";

    private ImageView imageView;
    private TextView id_sampah, pemilik;
    private EditText berat;
    private Button terima, tolak;

    private String kode;

    private Bitmap img;
    private Uri imgUri;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public void setKode(String kode) {
        this.kode = kode;
    }

    //    @Override
//    public void onStart() {
//        super.onStart();
//        Bundle bundle = getArguments();
//        String id = bundle.getString(DATA_DITERIMA);
//        if (bundle != null){
//            Toast.makeText(getActivity(),"id => " + id,Toast.LENGTH_LONG).show();
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_verifikasi, container, false);

        imageView = (ImageView) view.findViewById(R.id.img_preview_verifikasi);
        id_sampah = (TextView) view.findViewById(R.id.text_id_sampah_verifikasi);
        pemilik = (TextView) view.findViewById(R.id.text_pemilik_sampah_verifikasi);
        berat = (EditText) view.findViewById(R.id.input_berat_sampah_verifikasi);
        terima = (Button) view.findViewById(R.id.btn_terima_verifikasi);
        tolak = (Button) view.findViewById(R.id.btn_tolak_verifikasi);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dataSampah").child(kode);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Verifikasi model = dataSnapshot.getValue(Verifikasi.class);
                String id = model.getKodeSampah();
                String uid = model.getUidVolunteer();
                pemilik.setText(uid);
                id_sampah.setText(id);
                final String key = "-LP0K867PEr9ztRgfXoZ";

                databaseReference = firebaseDatabase.getReference("pengguna").child(uid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Verifikasi model = dataSnapshot.getValue(Verifikasi.class);
                        String nama = model.getNama();

                        Toast.makeText(getContext(), "Nama : " + nama, Toast.LENGTH_LONG).show();

//                        pemilik.setText(nama);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        terima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verfikasi = "sudah verifikasi";
                String berat_sampah = berat.getText().toString().trim();
                SimpleDateFormat date = new SimpleDateFormat("dd-MMMM-yyyy HH:mm");
                String tanggalSubmit = date.format(new Date());

                double poin = 10 * (Double.parseDouble(berat_sampah));
                System.out.println("poin sampah = " + poin);

                if (berat_sampah.isEmpty()) {
                    Toast.makeText(getContext(), "isi berat", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseDatabase.getReference("dataSampah").child(kode).child("statusVerifikasi").setValue(verfikasi);
                    firebaseDatabase.getReference("dataSampah").child(kode).child("beratSampah").setValue(berat_sampah);
                    firebaseDatabase.getReference("dataSampah").child(kode).child("poinSampah").setValue(poin);
                    firebaseDatabase.getReference("dataSampah").child(kode).child("tanggalSubmit").setValue(tanggalSubmit);
                    Toast.makeText(getContext(), "update berhasil", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        id_sampah.setText(kode);
        return view;
    }
}
