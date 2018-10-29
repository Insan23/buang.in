package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.adapter.PerusahaanAdapter;
import id.ac.unej.ilkom.ods.buangin.admin.model.Pengguna;

public class FragmentPerusahaan extends Fragment {
    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private PerusahaanAdapter adapter;
    private List<Pengguna> penggunaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perusahaan, container, false);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("pengguna");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                penggunaList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Pengguna model = dataSnapshot1.getValue(Pengguna.class);
                    String level = model.getLevel();
                    String nama = model.getNama();
                    String instansi = model.getNamaInstansi();
                    String telp = model.getTelp();
                    String alamat = model.getAlamat();
                    String email = model.getEmail();
                    if (level.equalsIgnoreCase("perusahaan")) {
                        model = new Pengguna(null, nama, null, instansi, email, null, alamat, telp, null, null);
                        penggunaList.add(model);
                    } else {

                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_perusahaan);
        penggunaList = new ArrayList<>();
        adapter = new PerusahaanAdapter(getContext(), penggunaList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }
}