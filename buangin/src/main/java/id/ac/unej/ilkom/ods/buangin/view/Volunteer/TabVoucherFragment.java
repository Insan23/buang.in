package id.ac.unej.ilkom.ods.buangin.view.Volunteer;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.BeliVoucherAdapter;
import id.ac.unej.ilkom.ods.buangin.model.BaseApi;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucher;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucherVolunteer;
import id.ac.unej.ilkom.ods.buangin.util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabVoucherFragment extends Fragment {
    private Button tukar;

    private RecyclerView recyclerView;
    private BeliVoucherAdapter adapter;
    private List<ModelVoucher> listVoucher;
    private DatabaseReference dbRef;
    private View view;

    private TextView listKosong;

    public TabVoucherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_tab_voucher, container, false);
        this.view = view;
        dbRef = FirebaseDatabase.getInstance().getReference();
        listKosong = view.findViewById(R.id.teks_kosong);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_voucher);
        listVoucher = new ArrayList<>();

        dbRef.child("dataVoucher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listVoucher.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        ModelVoucher perVoucher = data.getValue(ModelVoucher.class);
                        int jml = Integer.valueOf(perVoucher.getJumlahKuota());
                        Log.d("TABVOU", "Jumlah Voucher:" + jml + ", jml voucher string" + perVoucher.getJumlahKuota());
                        Log.d("TabVou", "status voucher:" + perVoucher.getStatusVoucher());
                        if (perVoucher.getStatusVoucher().equals(ModelVoucher.VOUCHER_BERLAKU) && jml > 0) {
                            perVoucher.setKey(data.getKey());
                            listVoucher.add(perVoucher);
                        } else {
                            Log.d("TabVou", "Voucher kosong");
                            if (listVoucher.size() == 0) {
                                recyclerView.setVisibility(View.GONE);
                                listKosong.setVisibility(View.VISIBLE);
                            }
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

            }
        });

        adapter = new BeliVoucherAdapter(listVoucher, new BeliVoucherAdapter.OnItemClickListener() {

            private boolean bolehBeli;

            @Override
            public void onItemClick(final ModelVoucher model) {
                bolehBeli = false;
                prosesBeli(model);
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }


    private void prosesBeli(final ModelVoucher voucher) {
        loading(true);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext()).setTitle("Beli Voucher").setMessage("Yakin ingin membeli voucher " + voucher.getNamaVoucher() + " seharga " + voucher.getHargaPoin() + " poin?").setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {

                dbRef = FirebaseDatabase.getInstance().getReference().child(BaseApi.TABEL_VOUCHER).child(voucher.getKey()).child("jumlahKuota");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final int jml = Integer.valueOf(dataSnapshot.getValue().toString());
                        if (jml > 0) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            final String kode = voucher.getUidMitra().substring(0, 3) + user.getUid().substring(0, 3) + Util.waktuEpochSekarang().toUpperCase();
                            ModelVoucherVolunteer beli = new ModelVoucherVolunteer("", voucher.getNamaMitra(), kode, voucher.getUrl_foto(), voucher.getNamaVoucher(), voucher.getDeskripsi(), voucher.getHargaPoin(), ModelVoucherVolunteer.VOUCHER_DIBELI);

                            dbRef = FirebaseDatabase.getInstance().getReference();
                            dbRef.child(BaseApi.TABEL_VOUCHER_VOLUNTEER).push().setValue(beli).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    AlertDialog.Builder dialogBerhasil = new AlertDialog.Builder(getContext()).setTitle("Berhasil").setMessage("Pembelian Berhasil, Kode Voucher Anda : " + kode + "\nCek Voucher Anda Di Tab Riwayat").setPositiveButton("kembali", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            updateVoucher(voucher.getKey(), jml);
                                            dialog.dismiss();
                                        }
                                    });
                                    dialogBerhasil.show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TabVoucherFragment.class.getSimpleName(), "Gagal mengirim data:" + e.getMessage());
                                    Toast.makeText(getContext(), "Gagal membeli voucher", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            });
                            loading(false);
                        } else {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext()).setMessage("Kuota voucher sudah habis").setPositiveButton("kembali", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    private void loading(boolean load) {
        if (load) {
            view.findViewById(R.id.loading_overlay).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.loading_overlay).setVisibility(View.GONE);
        }
    }

    private void updateVoucher(String key, int kuota) {
        String jumlahBaru = String.valueOf(kuota - 1);
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child(BaseApi.TABEL_VOUCHER).child(key).child("jumlahKuota").setValue(jumlahBaru);
    }

}
