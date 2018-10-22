package id.ac.unej.ilkom.ods.buangin.View.BankSampah;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Adapter.bs_daftarPerusahaan_adapter;
import id.ac.unej.ilkom.ods.buangin.Model.bs_daftarPerusahaan_model;
import id.ac.unej.ilkom.ods.buangin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarPerusahaanFragment extends Fragment {

    private RecyclerView recyclerView;
    private bs_daftarPerusahaan_adapter adapter;
    private List<bs_daftarPerusahaan_model> modelList;


    public DaftarPerusahaanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_sampah_daftar_perusahaan, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarPerusahaan);
        modelList = new ArrayList<>();
        adapter = new bs_daftarPerusahaan_adapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        daftarPerusahaan();

        return view;
    }

    private void daftarPerusahaan() {
        int[] pic = {
                R.drawable.perusahaan_01,
                R.drawable.perusahaan_02,
                R.drawable.perusahaan_03,
                R.drawable.perusahaan_04,
                R.drawable.perusahaan_05,
                R.drawable.perusahaan_06
        };

        bs_daftarPerusahaan_model a;
        a = new bs_daftarPerusahaan_model("Suka Maju", "Moh. Vian", "Jl. Jawa Gg. 10", pic[0]);
        modelList.add(a);
        a = new bs_daftarPerusahaan_model("Cipta Sejati", "Agung Prayogo", "Jl. Bengawan Solo Gg. 05", pic[1]);
        modelList.add(a);
        a = new bs_daftarPerusahaan_model("Tanah Hijau", "Iin Indarwati", "Jl. Kalimantan Gg. 02", pic[2]);
        modelList.add(a);
        a = new bs_daftarPerusahaan_model("Sinar Terang", "Sukmawati", "Jl. Jawa Gg. 04", pic[3]);
        modelList.add(a);
        a = new bs_daftarPerusahaan_model("Cipta Linkungan Sejati", "Aleq", "Jl. Slamet Riyadi Gg. 10", pic[4]);
        modelList.add(a);
        a = new bs_daftarPerusahaan_model("Melati Indah", "Ahmad Sukadi", "Jl. Karimata Gg. 10", pic[5]);
        modelList.add(a);

        adapter.notifyDataSetChanged();
    }

}
