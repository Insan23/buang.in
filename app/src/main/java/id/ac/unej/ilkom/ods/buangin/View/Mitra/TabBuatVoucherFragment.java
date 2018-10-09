package id.ac.unej.ilkom.ods.buangin.View.Mitra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.ac.unej.ilkom.ods.buangin.R;

public class TabBuatVoucherFragment extends Fragment {

    private EditText namaProduk, deskripsi, poin, kuota;
    private ImageView voucher;
    private Button tambah, batal;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    private DatabaseReference rootRef, demoRef;

    public TabBuatVoucherFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mitra_tab_buat_voucher, container, false);

        namaProduk = (EditText) view.findViewById(R.id.input_voucher_namaProduk);
        deskripsi = (EditText) view.findViewById(R.id.input_ubahHarga_deskripsi);
        poin = (EditText) view.findViewById(R.id.input_voucher_poin);
        kuota = (EditText) view.findViewById(R.id.input_voucher_jumlahKuota);
        voucher = (ImageView) view.findViewById(R.id.img_buatVoucher);

        batal = (Button) view.findViewById(R.id.btn_voucher_batal);
        tambah = (Button) view.findViewById(R.id.btn_voucher_tambah);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNama = namaProduk.getText().toString().trim();
                String strDeskripsi = deskripsi.getText().toString().trim();
                String strPoin = poin.getText().toString().trim();
                String strKuota = kuota.getText().toString().trim();
                String strID = firebaseAuth.getUid();
                Log.e("TabBuatVoucher", "strIDVoucher : " + strID);

                rootRef = FirebaseDatabase.getInstance().getReference();
                demoRef = rootRef.child("dataVoucher").child(strID);

                if (strNama.isEmpty()) {
                    namaProduk.requestFocus();
                    namaProduk.setError("Isi terlebih dahulu");
                } else if (strDeskripsi.isEmpty()) {
                    deskripsi.requestFocus();
                    deskripsi.setError("Isi terlebih dahulu");
                } else if (strPoin.isEmpty()) {
                    poin.requestFocus();
                    poin.setError("Isi terlebih dahulu");
                } else if (strKuota.isEmpty()) {
                    kuota.requestFocus();
                    kuota.setError("Isi terlebih dahulu");
                } else {
                    demoRef.push().setValue(strNama);
                    demoRef.push().setValue(strDeskripsi);
                    demoRef.push().setValue(strPoin);
                    demoRef.push().setValue(strKuota);
                }
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Voucher");
                builder.setMessage("Batal menambahkan voucher?");
                builder.setCancelable(false);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        namaProduk.setText("");
                        deskripsi.setText("");
                        poin.setText("");
                        kuota.setText("");
                        voucher.setImageResource(R.drawable.bg_img_voucher);
                    }
                });
            }
        });
        return view;
    }
}
