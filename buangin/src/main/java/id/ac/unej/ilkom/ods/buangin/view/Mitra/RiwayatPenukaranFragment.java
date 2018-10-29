package id.ac.unej.ilkom.ods.buangin.view.Mitra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.m_riwayat_adapter;
import id.ac.unej.ilkom.ods.buangin.model.m_riwayat_model;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatPenukaranFragment extends Fragment {

    private RecyclerView recyclerView;
    private m_riwayat_adapter adapter;
    private List<m_riwayat_model> modelList;
    private EditText kodeVoucher;
    private ImageButton hapusKode, cekKode;

    public RiwayatPenukaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeMitra) getActivity()).setActionBarTitle("Riwayat Penukaran");
        View view = inflater.inflate(R.layout.fragment_mitra_riwayat_penukaran, container, false);

        kodeVoucher = (EditText) view.findViewById(R.id.mitra_input_kode_voucher);
        hapusKode = (ImageButton) view.findViewById(R.id.mitra_btn_hapus_kode_voucher);
        cekKode = (ImageButton) view.findViewById(R.id.mitra_btn_cek_voucher);

        cekKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringKode = kodeVoucher.getText().toString().trim();
                Boolean voucherAda = true;
                if (stringKode.isEmpty()) {
                    kodeVoucher.requestFocus();
                    kodeVoucher.setError("Masukkan kode voucher");
                    voucherAda = false;
                } else {
                    voucherAda = true;
                }

                if (voucherAda) {
                    DialogVoucher voucher = new DialogVoucher();
                    voucher.setKodeVoucher(stringKode);
                    voucher.show(getChildFragmentManager(), "voucher");
                }
            }
        });

        hapusKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kodeVoucher.setText("");
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_riwayatPenukaran);
        modelList = new ArrayList<>();
        adapter = new m_riwayat_adapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        daftarRiwayat();

        return view;
    }

    private void daftarRiwayat() {
        int[] pic = {
                R.drawable.voucher,
                R.drawable.voucher_01,
                R.drawable.voucher_02,
                R.drawable.voucher_03,
                R.drawable.voucher_04,
                R.drawable.voucher_05,
                R.drawable.voucher_06,
                R.drawable.voucher_07,
        };

        m_riwayat_model a;
        a = new m_riwayat_model("Seblak Abah", "30", "23 September 2018", pic[0]);
        modelList.add(a);
        a = new m_riwayat_model("Big Burger", "30", "23 September 2018", pic[2]);
        modelList.add(a);
        a = new m_riwayat_model("Lempong", "60", "25 September 2018", pic[4]);
        modelList.add(a);
        a = new m_riwayat_model("Seblak Abah", "100", "10 September 2018", pic[0]);
        modelList.add(a);
        a = new m_riwayat_model("Seblak Abah", "40", "23 September 2018", pic[0]);
        modelList.add(a);
        a = new m_riwayat_model("Seblak Abah", "30", "26 September 2018", pic[0]);
        modelList.add(a);

        adapter.notifyDataSetChanged();
    }

}
