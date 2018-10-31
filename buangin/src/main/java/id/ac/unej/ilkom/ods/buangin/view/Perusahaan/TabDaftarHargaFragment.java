package id.ac.unej.ilkom.ods.buangin.view.Perusahaan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.p_ubahHarga_adapter;
import id.ac.unej.ilkom.ods.buangin.model.p_ubahHarga_model;

public class TabDaftarHargaFragment extends Fragment {
    private RecyclerView recyclerView;
    private p_ubahHarga_adapter adapter;
    private List<p_ubahHarga_model> modelList;

    public TabDaftarHargaFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perusahaan_tab_daftar_harga, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarHarga);
        modelList = new ArrayList<>();
        adapter = new p_ubahHarga_adapter(getActivity(), modelList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
