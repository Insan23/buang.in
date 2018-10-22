package id.ac.unej.ilkom.ods.buangin.View.Volunteer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Adapter.v_poin_adapter;
import id.ac.unej.ilkom.ods.buangin.Model.v_poin_model;
import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.Adapter.rv_poin_adapter;
import id.ac.unej.ilkom.ods.buangin.Model.cv_poin_model;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabPoinFragment extends Fragment {

    private RecyclerView recyclerView;
    private v_poin_adapter poinAdapter;
    private List<v_poin_model> modelList;

    public TabPoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_tab_poin, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_poin);
        modelList = new ArrayList<>();
        poinAdapter = new v_poin_adapter(getActivity(), modelList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(poinAdapter);

        daftarpoin();

        return view;
    }

    private void daftarpoin() {
        v_poin_model a = new v_poin_model("21 September 2018", "40", "2");
        modelList.add(a);
        a = new v_poin_model("22 September 2018", "40", "4");
        modelList.add(a);
        a = new v_poin_model("23 September 2018", "40", "4");
        modelList.add(a);
        a = new v_poin_model("24 September 2018", "40", "4");
        modelList.add(
                a);
        a = new v_poin_model("25 September 2018", "40", "4");
        modelList.add(a);
        a = new v_poin_model("26 September 2018", "10", "4");
        modelList.add(a);
        a = new v_poin_model("27 September 2018", "50", "5");
        modelList.add(a);
        a = new v_poin_model("28 September 2018", "60", "3");
        modelList.add(a);
        a = new v_poin_model("29 September 2018", "40", "4");
        modelList.add(a);
        a = new v_poin_model("20 September 2018", "70", "7");
        modelList.add(a);
        a = new v_poin_model("01 September 2018", "80", "4");
        modelList.add(a);
        a = new v_poin_model("02 September 2018", "40", "4");
        modelList.add(a);

        poinAdapter.notifyDataSetChanged();
    }

}
