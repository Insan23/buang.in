package id.ac.unej.ilkom.ods.buangin.view.Volunteer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class TabVoucherFragment extends Fragment {
    private Button tukar;

    private RecyclerView recyclerView;
    private VoucherAdapter adapter;
    private List<ModelVoucher> modelList;
    private DatabaseReference dbRef;

    public TabVoucherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_tab_voucher, container, false);
        dbRef = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_voucher);
        modelList = new ArrayList<>();

        dbRef.child("dataVoucher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter = new VoucherAdapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
