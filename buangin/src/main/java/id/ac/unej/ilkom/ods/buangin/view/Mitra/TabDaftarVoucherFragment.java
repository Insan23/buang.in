package id.ac.unej.ilkom.ods.buangin.view.Mitra;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.VoucherAdapter;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucher;
import id.ac.unej.ilkom.ods.buangin.util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabDaftarVoucherFragment extends Fragment {

    private static final String TAG = TabDaftarVoucherFragment.class.getSimpleName();

    private List<ModelVoucher> listVoucher;
    private RecyclerView recyclerView;
    private TextView listKosong;
    private ModelVoucher voucher;

    private DatabaseReference dbRef;
    private FirebaseAuth auth;

    public TabDaftarVoucherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((HomeMitra) getActivity()).setActionBarTitle("Daftar ModelVoucher");
        View view = inflater.inflate(R.layout.fragment_mitra_tab_daftar_voucher, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarVoucher);
        listVoucher = new ArrayList<>();
        listKosong = (TextView) view.findViewById(R.id.teks_kosong);

        final String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dbRef = FirebaseDatabase.getInstance().getReference(Util.DATA_VOUCHER_REFERENCE);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listVoucher.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        ModelVoucher perVoucher = data.getValue(ModelVoucher.class);
                        String uid = perVoucher.getUidMitra();
                        if (uid.equals(UID)) {
                            listVoucher.add(perVoucher);
                        }
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
//                Toast.makeText(getContext(), "Gagal Membaca Data", Toast.LENGTH_LONG).show();
            }
        });

        VoucherAdapter adapter = new VoucherAdapter(getContext(), listVoucher, new VoucherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ModelVoucher model) {
                Toast.makeText(getContext(), "Tentukan aksi saat klik voucher", Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}
