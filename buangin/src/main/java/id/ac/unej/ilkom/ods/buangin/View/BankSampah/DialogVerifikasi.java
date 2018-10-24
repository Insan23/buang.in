package id.ac.unej.ilkom.ods.buangin.View.BankSampah;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Model.Verifikasi;
import id.ac.unej.ilkom.ods.buangin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogVerifikasi extends DialogFragment {

    final static String DATA_DITERIMA = "data_diterima";

    private ImageView imageView;
    private TextView id_sampah, pemilik, status, poin, textHarga;
    private EditText berat, sampah, harga;
    private Button terima, tolak;
    private Spinner jenisSampah;
    private ImageButton cekHarga;

    private String kode;

    private double
            hargaBotolPlastik,
            hargaKertas,
            poinBotolPlastik,
            poinKertas,
            totalPoin,
            finalTotalPoin,
            hargaSampah;

    private Bitmap img;
    private Uri imgUri;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

//    private static final String[] jenis = {"botol plastik", "kertas", "jenis lain"};

    public void setKode(String kode) {
        this.kode = kode;
    }

    public DialogVerifikasi() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_verifikasi, container, false);

        imageView = (ImageView) view.findViewById(R.id.img_preview_verifikasi);
        id_sampah = (TextView) view.findViewById(R.id.text_id_sampah_verifikasi);
        pemilik = (TextView) view.findViewById(R.id.text_pemilik_sampah_verifikasi);
        berat = (EditText) view.findViewById(R.id.input_berat_sampah_verifikasi);
        status = (TextView) view.findViewById(R.id.text_status_verifikasi);
        jenisSampah = (Spinner) view.findViewById(R.id.spin_jenis_sampah);
        sampah = (EditText) view.findViewById(R.id.input_jenis_sampah);
        harga = (EditText) view.findViewById(R.id.input_harga_sampah);
        cekHarga = (ImageButton) view.findViewById(R.id.btn_cek_harga);
        poin = (TextView) view.findViewById(R.id.text_poin_verifikasi);
        textHarga = (TextView) view.findViewById(R.id.text_harga_verifikasi);

        terima = (Button) view.findViewById(R.id.btn_terima_verifikasi);
        tolak = (Button) view.findViewById(R.id.btn_tolak_verifikasi);

        berat.setText("0");

        sampah.setEnabled(false);
        harga.setEnabled(false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dataSampah").child(kode);

        List<String> list_sampah = new ArrayList<String>();
        list_sampah.add("Botol Plastik");
        list_sampah.add("Kertas");
        list_sampah.add("Jenis Lain");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list_sampah);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisSampah.setSelection(0);
        jenisSampah.setAdapter(arrayAdapter);

//        cekHarga.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String jenis_sampah = String.valueOf(jenisSampah.getSelectedItem());
//
//            }
//        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Verifikasi model = dataSnapshot.getValue(Verifikasi.class);
                final String stringStatus = model.getStatusVerifikasi();
                final String stringUID = model.getUidVolunteer();

                id_sampah.setText(kode);
                status.setText(stringStatus);
                databaseReference = firebaseDatabase.getReference("pengguna").child(stringUID);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Verifikasi verifikasi = dataSnapshot1.getValue(Verifikasi.class);
                            String stringNama = verifikasi.getNama();
//                            String stringPoin = verifikasi.getUidVolunteer();
                            pemilik.setText(stringNama);
                            System.out.println("sampah nama : " + stringNama);
                            System.out.println("sampah kode : " + kode);
                            System.out.println("sampah status : " + stringStatus);
                            System.out.println("sampah UID : " + stringUID);

                            cekHarga.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String jenis_sampah = String.valueOf(jenisSampah.getSelectedItem());
                                    final String stringHargaSampah = harga.getText().toString().trim();
                                    final String stringBeratSampah = berat.getText().toString().trim();
//                                    final double doubleBeratSampah = Double.valueOf(stringBeratSampah);

                                    hargaBotolPlastik = 3600;
                                    hargaKertas = 950;
                                    poinBotolPlastik = hargaBotolPlastik / 100;
                                    poinKertas = hargaKertas / 100;
                                    if (jenis_sampah.equalsIgnoreCase("botol plastik")) {
                                        totalPoin = poinBotolPlastik;
                                        hargaSampah = totalPoin * 100;

                                        Toast.makeText(getContext(), "jenis sampah : " + jenis_sampah + ", getSampah " + sampah.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                                        poin.setText(String.valueOf(totalPoin * Double.valueOf(stringBeratSampah)) + " poin");
                                        sampah.setText(jenis_sampah);

                                        sampah.setEnabled(false);
                                        harga.setEnabled(false);

                                        textHarga.setText("Rp. " + String.valueOf(hargaSampah * Double.valueOf(stringBeratSampah)));
                                        harga.setText(String.valueOf(hargaSampah));
                                    } else if (jenis_sampah.equalsIgnoreCase("kertas")) {
                                        totalPoin = poinKertas;
                                        hargaSampah = totalPoin * 100;

                                        sampah.setEnabled(false);
                                        harga.setEnabled(false);

                                        Toast.makeText(getContext(), "jenis sampah : " + jenis_sampah + ", getSampah " + sampah.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                                        textHarga.setText("Rp. " + String.valueOf(hargaSampah * Double.valueOf(stringBeratSampah)));
                                        poin.setText(String.valueOf(totalPoin * Double.valueOf(stringBeratSampah)) + " poin");
                                        sampah.setText(jenis_sampah);
                                        harga.setText(String.valueOf(hargaSampah));
                                    } else if (jenis_sampah.equalsIgnoreCase("jenis lain")) {
                                        sampah.setEnabled(true);
                                        harga.setEnabled(true);

                                        Toast.makeText(getContext(), "jenis sampah : " + jenis_sampah + ", getSampah " + sampah.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                                        textHarga.setText("Rp. " + String.valueOf(hargaSampah * Double.valueOf(stringBeratSampah)));
                                        Double doubleHargaSampah = Double.valueOf(stringHargaSampah);
                                        totalPoin = doubleHargaSampah / 100;
                                        hargaSampah = totalPoin * 100;
                                        poin.setText(String.valueOf(totalPoin * Double.valueOf(stringBeratSampah)) + " poin");
                                    }

//                                    final double finalHargaSampah = hargaSampah;
//                                    finalTotalPoin = totalPoin;
//                                    final double doublePoin = finalTotalPoin * (Double.parseDouble(stringBeratSampah));
//                                    Toast.makeText(getContext(), "doublePoin : " + doublePoin +
//                                            ", beratSampah : " + Double.parseDouble(stringBeratSampah) +
//                                            ", hargaSampah : " + finalHargaSampah, Toast.LENGTH_LONG).show();

                                    terima.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Boolean bolehVerifikasi = true;
                                            String stringBerat = berat.getText().toString().trim();
                                            String stringJenisSampah = sampah.getText().toString().trim();
                                            String stringHargaSampah = harga.getText().toString().trim();
                                            Double doubleHarga = Double.valueOf(stringHargaSampah);
                                            Double doublePoinSampah = doubleHarga / 100;
                                            Double dooubleTotalHarga = doubleHarga * Double.valueOf(stringBerat);
                                            Double doubleTotalPoin = (Double.valueOf(doubleHarga) / 100) * Double.valueOf(stringBerat);

                                            String stringVerifikasi = "Sudah diverifikasi";
                                            String stringBatalverifikasi = "belum diverifikasi";
                                            if (sampah.getText().toString().trim().isEmpty()) {
                                                sampah.requestFocus();
                                                bolehVerifikasi = false;
                                                Toast.makeText(getContext(), "Masukkan jenis sampah", Toast.LENGTH_SHORT).show();
                                            } else {
                                                bolehVerifikasi = true;
                                            }
                                            if (harga.getText().toString().trim().isEmpty() || harga.getText().toString().trim().equalsIgnoreCase("0")) {
                                                harga.requestFocus();
                                                bolehVerifikasi = false;
                                                Toast.makeText(getContext(), "Masukkan harga sampah", Toast.LENGTH_SHORT).show();
                                            } else {
                                                bolehVerifikasi = true;
                                            }
                                            if (berat.getText().toString().trim().isEmpty() || berat.getText().toString().trim().equalsIgnoreCase("0")) {
                                                berat.requestFocus();
                                                bolehVerifikasi = false;
                                                Toast.makeText(getContext(), "Masukkan berat sampah", Toast.LENGTH_SHORT).show();
                                            } else {
                                                bolehVerifikasi = true;
                                            }
                                            if (stringStatus.equalsIgnoreCase(stringVerifikasi)) {
                                                bolehVerifikasi = false;
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                                        .setCancelable(false)
                                                        .setTitle("Gagal Verifikasi")
                                                        .setMessage("Maaf data sampah dengan kode " + kode + "sudah diverifikasi")
                                                        .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                                AlertDialog alertDialog = builder.create();
                                                alertDialog.show();
                                            } else {
                                                bolehVerifikasi = true;
                                            }

                                            if (bolehVerifikasi) {
                                                SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                                                String stringTanggalSubmit = data.format(new Date());
                                                if (stringBerat.isEmpty()) {
                                                    berat.requestFocus();
                                                    berat.setError("Masukan berat sampah terlebih dahulu");
                                                } else {
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("jenisSampah").setValue(stringJenisSampah);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("statusVerifikasi").setValue(stringVerifikasi);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("beratSampah").setValue(stringBerat);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("poinSampah").setValue(String.valueOf(doubleTotalPoin));
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("tanggalSubmit").setValue(stringTanggalSubmit);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("hargaSampah").setValue(String.valueOf(dooubleTotalHarga));

                                                    Toast.makeText(getContext(), "jenis : " + stringJenisSampah +
                                                                    ", berat : " + stringBerat + ", poin : " + String.valueOf(doubleTotalPoin) +
                                                                    ", harga : " + String.valueOf(dooubleTotalHarga)
                                                            , Toast.LENGTH_LONG).show();

                                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                                            .setCancelable(false)
                                                            .setTitle("Sukses Verifikasi")
                                                            .setMessage("Terima kasih, data sampah dengan kode " + kode + " telah berhasil diverifikasi")
                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                    AlertDialog alertDialog = builder.create();
                                                    alertDialog.show();
                                                    dismiss();
                                                }
                                            }
                                        }
                                    });

                                    tolak.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dismiss();
                                        }
                                    });

//                                    cekHarga.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            String stringSampah = sampah.getText().toString().trim();
//                                            String stringBerat = berat.getText().toString().trim();
//                                            String stringHarga = harga.getText().toString().trim();
//
//                                            if (stringSampah.isEmpty()) {
//                                                sampah.requestFocus();
//                                                sampah.setError("Masukkan jenis sampah");
//                                            } else {
//                                                if (stringBerat.isEmpty()) {
//                                                    berat.requestFocus();
//                                                    berat.setError("Masukkan berat sampah");
//                                                } else {
//                                                    if (stringHarga.isEmpty()) {
//                                                        harga.requestFocus();
//                                                        harga.setError("Masukkan harga sampah");
//                                                    } else {
//                                                        double doubleBerat = Double.valueOf(stringBerat);
//                                                        double doubleHarga = Double.valueOf(stringHarga);
//                                                        double doubleTotalHarga = doubleHarga * doubleBerat;
//                                                        double doublePoin = doubleHarga / 100;
//                                                        double doubleTotalPoin = doublePoin * doubleBerat;
//                                                        poin.setText(String.valueOf(doubleTotalPoin));
//                                                        textHarga.setText(String.valueOf(doubleTotalHarga));
//
//                                                        Toast.makeText(getContext(), "berat : " + doubleBerat +
//                                                                ", harga : " + doubleHarga + ", TotalHarga : " + doubleTotalHarga +
//                                                                ", poin : " + doublePoin + ", TotalPoin : " + doubleTotalPoin, Toast.LENGTH_LONG).show();
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    });
                                }
                            });

//                            if (stringStatus.equalsIgnoreCase("sudah diverifikasi")) {
//                                terima.setEnabled(false);
//                            } else if (stringStatus.equalsIgnoreCase("menunggu verifikasi")) {
//                                terima.setEnabled(true);
//                            }

//                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
//                                    R.layout.support_simple_spinner_dropdown_item, jenis);
//                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//                            jenisSampah.setAdapter(adapter);
//                            jenisSampah.setSelection(0);
//                            jenisSampah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                @Override
//                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                    final String stringSpinjenisSampah = parent.getItemAtPosition(position).toString();
////                                    final String stringSpinjenisSampah=String.valueOf(jenisSampah.getSelectedItem());
////                                    Toast.makeText(getContext(),"jenis : "+stringSpinjenisSampah,Toast.LENGTH_SHORT).show();
//                                    final String stringJenis = sampah.getText().toString().trim();
//
//                                }
//
//                                @Override
//                                public void onNothingSelected(AdapterView<?> parent) {
//
//                                }
//                            });
                        }
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
    }
}
