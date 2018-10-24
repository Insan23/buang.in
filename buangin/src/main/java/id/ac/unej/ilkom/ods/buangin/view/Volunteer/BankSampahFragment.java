package id.ac.unej.ilkom.ods.buangin.view.Volunteer;


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
import id.ac.unej.ilkom.ods.buangin.adapter.v_daftarBank_adapter;
import id.ac.unej.ilkom.ods.buangin.model.v_daftarBank_model;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankSampahFragment extends Fragment {

    private RecyclerView recyclerView;
    private v_daftarBank_adapter adapter;
    private List<v_daftarBank_model> modelList;

    public BankSampahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeVolunteer) getActivity()).setActionBarTitle("Bank ModelSampah");
        View view = inflater.inflate(R.layout.fragment_volunteer_daftar_bank_sampah, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarBankSampah);
        modelList = new ArrayList<>();
        adapter = new v_daftarBank_adapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        daftarBankSampah();

        return view;
    }

    private void daftarBankSampah() {
        int[] pic = {
                R.drawable.banksampah_01,
                R.drawable.banksampah_2,
                R.drawable.banksampah_03,
                R.drawable.banksampah_04,
                R.drawable.banksampah_05,
                R.drawable.banksampah_06
        };

        v_daftarBank_model a = new v_daftarBank_model("Suka Maju", "Adi Surya", "Jl. Bengawan Solo Gg. 2", pic[0]);
        modelList.add(a);
        a = new v_daftarBank_model("Cipta Lingkungan", "Pramojo", "Jl. Slamet Gg. 3", pic[1]);
        modelList.add(a);
        a = new v_daftarBank_model("Bank Prapto", "Riki", "Jl. Bengawan Solo Gg. 25", pic[2]);
        modelList.add(a);
        a = new v_daftarBank_model("Bank Dermawan", "Rukanah", "Jl. Kalimantan Gg. 5", pic[3]);
        modelList.add(a);
        a = new v_daftarBank_model("Cinta Bersih", "Indah", "Jl. Jawa Gg. 2", pic[4]);
        modelList.add(a);
        a = new v_daftarBank_model("Cinta Kasih", "Hari", "Jl. Bengawan Solo Gg. 3", pic[5]);
        modelList.add(a);

        adapter.notifyDataSetChanged();
    }
}
