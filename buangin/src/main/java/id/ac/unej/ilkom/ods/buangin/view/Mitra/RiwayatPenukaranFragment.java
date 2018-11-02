package id.ac.unej.ilkom.ods.buangin.view.Mitra;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.RiwayatAdapterMitra;
import id.ac.unej.ilkom.ods.buangin.model.BaseApi;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucherVolunteer;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatPenukaranFragment extends Fragment {

    private RecyclerView recyclerView;
    private RiwayatAdapterMitra adapter;
    private List<ModelVoucherVolunteer> modelList;
    private EditText kodeVoucher;
    private ImageButton hapusKode, cekKode;

    private DatabaseReference ref;
    private FirebaseAuth auth;

    public RiwayatPenukaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeMitra) getActivity()).setActionBarTitle("Riwayat Penukaran");
        View view = inflater.inflate(R.layout.fragment_mitra_riwayat_penukaran, container, false);
        ref = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        kodeVoucher = (EditText) view.findViewById(R.id.mitra_input_kode_voucher);
        hapusKode = (ImageButton) view.findViewById(R.id.mitra_btn_hapus_kode_voucher);
        cekKode = (ImageButton) view.findViewById(R.id.mitra_btn_cek_voucher);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_riwayatPenukaran);

        cekKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stringKode = kodeVoucher.getText().toString().trim();
                Boolean voucherAda = true;
                if (stringKode.isEmpty()) {
                    kodeVoucher.requestFocus();
                    kodeVoucher.setError("Masukkan kode voucher");
                    voucherAda = false;
                } else {
                    voucherAda = true;
                }

                if (voucherAda) {
                    ref.child(BaseApi.TABEL_VOUCHER_VOLUNTEER).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            boolean ketemu = false;
                            String key = "";
                            ModelVoucherVolunteer vouch = null;
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                ModelVoucherVolunteer voucher = data.getValue(ModelVoucherVolunteer.class);
                                if (voucher.getKodeVoucher().equalsIgnoreCase(stringKode)) {
                                    ketemu = true;
                                    key = data.getKey();
                                    vouch = voucher;
                                    break;
                                } else {
                                    ketemu = false;
                                }
                            }
                            if (ketemu) {
                                DialogVoucher voucher = new DialogVoucher();
                                voucher.setKodeVoucher(vouch, key);
                                voucher.show(getChildFragmentManager(), "voucher");
                            } else {

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        hapusKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kodeVoucher.setText("");
            }
        });

        modelList = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference(BaseApi.TABEL_VOUCHER_VOLUNTEER);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ModelVoucherVolunteer voucher = data.getValue(ModelVoucherVolunteer.class);
                    if (voucher.getStatusVoucher().equalsIgnoreCase(ModelVoucherVolunteer.VOUCHER_DITUKAR)) {
                        modelList.add(voucher);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Riwayat", "Gagal membaca voucher:" + databaseError.getDetails());
            }
        });

        adapter = new RiwayatAdapterMitra(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }


}
