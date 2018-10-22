package id.ac.unej.ilkom.ods.buangin.View.Perusahaan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Adapter.p_ubahHarga_adapter;
import id.ac.unej.ilkom.ods.buangin.Model.p_ubahHarga_model;
import id.ac.unej.ilkom.ods.buangin.R;

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

        daftarHarga();

        return view;
    }

    private void daftarHarga() {
        int[] pic = {
                R.drawable.sampah_01,
                R.drawable.sampah_02,
                R.drawable.sampah_03
        };
        p_ubahHarga_model a = new p_ubahHarga_model("kaleng", "Rudi", "2000", "Jl. Kembang Gg. 3", pic[0]);
        modelList.add(a);
        a = new p_ubahHarga_model("kertas", "Rudi", "1000", "Jl. Kembang Gg. 3", pic[0]);
        modelList.add(a);
        a = new p_ubahHarga_model("botol plastik", "Rudi", "500", "Jl. Kembang Gg. 3", pic[0]);
        modelList.add(a);

        adapter.notifyDataSetChanged();
    }
}
