package id.ac.unej.ilkom.ods.buangin.View.Perusahaan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.ac.unej.ilkom.ods.buangin.R;

public class TabUbahHargaFragment extends Fragment {
    private EditText namaProduk, deskripsi, hargaProduk;
    private Button tambah;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference rootRef, demoRef;
    private FirebaseDatabase firebaseDatabase;

    public TabUbahHargaFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perusahaan_tab_ubah_harga, container, false);

        namaProduk = (EditText) view.findViewById(R.id.input_ubahHarga_namaProduk);
        deskripsi = (EditText) view.findViewById(R.id.input_ubahHarga_deskripsi);
        hargaProduk = (EditText) view.findViewById(R.id.input_ubahHarga_hargaBaru);

        tambah = (Button) view.findViewById(R.id.btn_ubahHarga_ubah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNama = namaProduk.getText().toString().trim();
                String strHarga = hargaProduk.getText().toString().trim();
                String strDesk = deskripsi.getText().toString().trim();
                String strID = FirebaseAuth.getInstance().getUid();
//                tambah(strNama, strHarga, strDesk);

                rootRef = FirebaseDatabase.getInstance().getReference();
                demoRef = rootRef.child("dataHargaSampah").child(strID);

                if (strNama.isEmpty()) {
                    namaProduk.requestFocus();
                    namaProduk.setError("Isi terlebih dahulu");
                } else if (strHarga.isEmpty()) {
                    deskripsi.requestFocus();
                    deskripsi.setError("Isi terlebih dahulu");
                } else if (strDesk.isEmpty()) {
                    hargaProduk.requestFocus();
                    hargaProduk.setError("Isi terlebih dahulu");
                } else {
                    demoRef.push().setValue(strNama);
                }
            }
        });

        initFirebase();

        return view;
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
