package id.ac.unej.ilkom.ods.buangin.View.Mitra;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Adapter.TabAdapter;
import id.ac.unej.ilkom.ods.buangin.Adapter.m_daftarVoucher_adapter;
import id.ac.unej.ilkom.ods.buangin.Adapter.p_ubahHarga_adapter;
import id.ac.unej.ilkom.ods.buangin.Model.m_daftarVoucher_model;
import id.ac.unej.ilkom.ods.buangin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabDaftarVoucherFragment extends Fragment {

    private RecyclerView recyclerView;
    private m_daftarVoucher_adapter adapter;
    private List<m_daftarVoucher_model> modelList;

    public TabDaftarVoucherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeMitra) getActivity()).setActionBarTitle("Daftar Voucher");
        View view = inflater.inflate(R.layout.fragment_mitra_tab_daftar_voucher, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarVoucher);
        modelList = new ArrayList<>();
        adapter = new m_daftarVoucher_adapter(getActivity(), modelList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        daftarVoucher();
        return view;
    }

    private void daftarVoucher() {
        int pic = R.drawable.voucher;

        m_daftarVoucher_model a = new m_daftarVoucher_model("Seblak Abah", "30", "10", "Sampai 28 Oktober 2018", pic);
        modelList.add(a);
        a = new m_daftarVoucher_model("Seblak Abah", "30", "10", "Sampai 28 Oktober 2018", pic);
        modelList.add(a);
        a = new m_daftarVoucher_model("Seblak Abah", "20", "30", "Sampai 29 Oktober 2018", pic);
        modelList.add(a);
        a = new m_daftarVoucher_model("Seblak Abah", "100", "10", "Sampai 10 Oktober 2018", pic);
        modelList.add(a);
        a = new m_daftarVoucher_model("Seblak Abah", "40", "4", "Sampai 04 November 2018", pic);
        modelList.add(a);

        adapter.notifyDataSetChanged();
    }

}
