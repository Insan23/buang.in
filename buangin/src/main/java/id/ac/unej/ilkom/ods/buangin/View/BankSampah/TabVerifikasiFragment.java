package id.ac.unej.ilkom.ods.buangin.View.BankSampah;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Adapter.bs_verifikasi_adapter;
import id.ac.unej.ilkom.ods.buangin.Model.bs_verifikasi_model;
import id.ac.unej.ilkom.ods.buangin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabVerifikasiFragment extends Fragment {

    private RecyclerView recyclerView;
    private bs_verifikasi_adapter adapter;
    private List<bs_verifikasi_model> modelList;
    private EditText cari_kode;
    private Button cek;

    public TabVerifikasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_sampah_tab_verifikasi, container, false);

        cari_kode = (EditText) view.findViewById(R.id.input_kode);
        cek = (Button) view.findViewById(R.id.btn_cek_kode);
        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringKode = cari_kode.getText().toString().trim();

                String str = "-LP3ugLU-XGy8RN_lLY-";
                Toast.makeText(getContext(), stringKode, Toast.LENGTH_SHORT).show();
//                mCallback.passData(stringKode);
                DialogVerifikasi dialogVerifikasi = new DialogVerifikasi();
                dialogVerifikasi.setKode(stringKode);
                dialogVerifikasi.show(getChildFragmentManager(), "verifikasi");
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_verifikasi);
        modelList = new ArrayList<>();
        adapter = new bs_verifikasi_adapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        daftarVerifikasi();
        return view;
    }

    private void daftarVerifikasi() {
        int[] pic = {
                R.drawable.sampah_01,
                R.drawable.sampah_02,
                R.drawable.sampah_03,
                R.drawable.sampah_04,
                R.drawable.sampah0,
                R.drawable.sampah1,
                R.drawable.sampah2,
                R.drawable.sampah3
        };

        bs_verifikasi_model a;
        a = new bs_verifikasi_model(pic[0], "15 September 2018", "5", "01:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[2], "15 September 2018", "8", "01:10:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[1], "15 September 2018", "8", "00:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[5], "18 September 2018", "12", "00:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[3], "19 September 2018", "5", "01:20:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[3], "20 September 2018", "10", "00:50:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[2], "21 September 2018", "5", "00:10:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[0], "22 September 2018", "5", "01:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[1], "22 September 2018", "3", "01:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[1], "22 September 2018", "5", "01:00:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[6], "23 September 2018", "4", "02:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[7], "23 September 2018", "2", "01:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[5], "25 September 2018", "9", "02:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[2], "25 September 2018", "6", "01:30:20", "terima");
        modelList.add(a);
        a = new bs_verifikasi_model(pic[03], "26 September 2018", "3", "01:30:20", "terima");
        modelList.add(a);

        adapter.notifyDataSetChanged();
    }

}
