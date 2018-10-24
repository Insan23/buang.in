package id.ac.unej.ilkom.ods.buangin.View.Mitra;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Adapter.VoucherAdapter;
import id.ac.unej.ilkom.ods.buangin.Adapter.m_daftarVoucher_adapter;
import id.ac.unej.ilkom.ods.buangin.Model.Voucher;
import id.ac.unej.ilkom.ods.buangin.Model.m_daftarVoucher_model;
import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.Util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabDaftarVoucherFragment extends Fragment {

    private static final String TAG = TabDaftarVoucherFragment.class.getSimpleName();

    private List<Voucher> listVoucher;
    private RecyclerView recyclerView;
    private TextView listKosong;
    private Voucher voucher;

    private DatabaseReference dbRef;
    private FirebaseAuth auth;

    public TabDaftarVoucherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((HomeMitra) getActivity()).setActionBarTitle("Daftar Voucher");
        View view = inflater.inflate(R.layout.fragment_mitra_tab_daftar_voucher, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarVoucher);
        listVoucher = new ArrayList<>();
        listKosong = (TextView) view.findViewById(R.id.teks_kosong);

        dbRef = FirebaseDatabase.getInstance().getReference(Util.DATA_VOUCHER_REFERENCE);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Voucher perVoucher = data.getValue(Voucher.class);
                        listVoucher.add(perVoucher);
                    }
                    recyclerView.setVisibility(View.VISIBLE);
                    listKosong.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    listKosong.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Gagal Membaca Data" + databaseError.getDetails());
                Toast.makeText(getContext(), "Gagal Membaca Data", Toast.LENGTH_LONG).show();
            }
        });

        VoucherAdapter adapter = new VoucherAdapter(getContext(), listVoucher);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}
