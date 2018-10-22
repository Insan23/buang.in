package id.ac.unej.ilkom.ods.buangin.View.Perusahaan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.unej.ilkom.ods.buangin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    CardView mUbahHarga;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ((HomePerusahaan)getActivity()).setActionBarTitle("Menu Perusahaan");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perusahaan_home, container, false);
        mUbahHarga =(CardView)view.findViewById(R.id.cv_ubahHarga);
        mUbahHarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container_perusahaan, new UbahHargaFragment());
                ft.commit();
            }
        });
        return view;
    }
}
