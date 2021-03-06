package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.model.ModelHarga;

public class FragmentDaftarHarga extends Fragment {
    private ImageButton ubah_botol_plastik, ubah_kertas;
    private TextView jenisBotol, hargaBotol, jenisKertas, hargakertas, poinBotol, poinKertas;

    DialogUbahHarga ubahHarga;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public FragmentDaftarHarga() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftar_harga, container, false);

        hargaBotol = (TextView) view.findViewById(R.id.harga_sampah_botol_plastik);
        hargakertas = (TextView) view.findViewById(R.id.harga_sampah_kertas);
        jenisBotol = (TextView) view.findViewById(R.id.harga_jenis_botol_plastik);
        jenisKertas = (TextView) view.findViewById(R.id.harga_jenis_kertas);
        poinBotol = (TextView) view.findViewById(R.id.harga_poin_botol_plastik);
        poinKertas = (TextView) view.findViewById(R.id.harga_poin_kertas);
        ubah_botol_plastik = (ImageButton) view.findViewById(R.id.harga_btn_ubah_botol);
        ubah_kertas = (ImageButton) view.findViewById(R.id.harga_btn_ubah_kertas);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dataHarga");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getValue();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelHarga model = dataSnapshot1.getValue(ModelHarga.class);
                    String stringHargaBotol = model.getHargaBotolPlastik();
                    String stringHargaKertas = model.getHargaKertas();

                    hargaBotol.setText(stringHargaBotol);
                    hargakertas.setText(stringHargaKertas);

                    poinBotol.setText(String.valueOf(Double.valueOf(stringHargaBotol) / 100));
                    poinKertas.setText(String.valueOf(Double.valueOf(stringHargaKertas) / 100));

                    ubah_botol_plastik.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String strJenis = jenisBotol.getText().toString().trim();
                            String strHarga = hargaBotol.getText().toString().trim();
                            ubahHarga = new DialogUbahHarga();
                            ubahHarga.terimaData(strJenis, strHarga);
                            ubahHarga.show(getChildFragmentManager(), "ubah harga");
                        }
                    });
                    ubah_kertas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String strJenis = jenisKertas.getText().toString().trim();
                            String strHarga = hargakertas.getText().toString().trim();
                            ubahHarga = new DialogUbahHarga();
                            ubahHarga.terimaData(strJenis, strHarga);
                            ubahHarga.show(getChildFragmentManager(), "ubah harga");
                        }
                    });

                    System.out.println("harga botol plasti : " + stringHargaBotol);
                    System.out.println("harga kertas : " + stringHargaKertas);

                    Toast.makeText(getContext(), "botol : " + stringHargaBotol + ", kertas : " + stringHargaKertas, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Database error : ", databaseError.getMessage());
            }
        });

        return view;
    }
}
