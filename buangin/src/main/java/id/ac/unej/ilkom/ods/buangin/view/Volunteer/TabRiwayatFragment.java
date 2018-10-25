package id.ac.unej.ilkom.ods.buangin.view.Volunteer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.RiwayatAdapterVolunteer;
import id.ac.unej.ilkom.ods.buangin.adapter.VoucherAdapter;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucherVolunteer;

public class TabRiwayatFragment extends Fragment {
    private RecyclerView recyclerView;
    private RiwayatAdapterVolunteer adapter;
    private List<ModelVoucherVolunteer> voucherList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volunteer_tab_riwayat, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_riwayatVoucher);
        voucherList = new ArrayList<>();
        adapter = new RiwayatAdapterVolunteer(getActivity(), voucherList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        daftarVoucher();

        return view;
    }

    private void daftarVoucher() {
        ModelVoucherVolunteer a;
        a = new ModelVoucherVolunteer(null, "Seblak Abah", "asdasd", null, "Seblak Abah", null, "30" + " poin", "belum dipakai");
        voucherList.add(a);
        a = new ModelVoucherVolunteer(null, "Seblak Abah", "asdasd", null, "Seblak Abah", null, "30" + " poin", "belum dipakai");
        voucherList.add(a);

        adapter.notifyDataSetChanged();
    }
}
