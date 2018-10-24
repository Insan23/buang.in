package id.ac.unej.ilkom.ods.buangin.view.Volunteer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    public TabVoucherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_tab_voucher, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_voucher);
        modelList = new ArrayList<>();
        adapter = new VoucherAdapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        daftarVoucher();

        return view;
    }

//    private void daftarVoucher() {
//        int[] pic = {
//                R.drawable.voucher,
//                R.drawable.voucher_01,
//                R.drawable.voucher_02,
//                R.drawable.voucher_03,
//                R.drawable.voucher_04,
//                R.drawable.voucher_05,
//                R.drawable.voucher_06,
//                R.drawable.voucher_07
//        };
//
//        v_voucher_model a = new v_voucher_model("Seblak Abah", "100", "4", pic[0]);
//        modelList.add(a);
//        a = new v_voucher_model("Lebong", "100", "4", pic[1]);
//        modelList.add(a);
//        a = new v_voucher_model("Big Burger", "100", "10", pic[2]);
//        modelList.add(a);
//        a = new v_voucher_model("Seblak Abah", "100", "7", pic[0]);
//        modelList.add(a);
//        a = new v_voucher_model("Seblak Abah", "100", "2", pic[0]);
//        modelList.add(a);
//        a = new v_voucher_model("Sari Roti", "100", "12", pic[5]);
//        modelList.add(a);
//        a = new v_voucher_model("Macarina", "100", "4", pic[6]);
//        modelList.add(a);

//        adapter.notifyDataSetChanged();
//    }
}
