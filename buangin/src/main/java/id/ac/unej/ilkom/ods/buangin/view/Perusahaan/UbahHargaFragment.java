package id.ac.unej.ilkom.ods.buangin.view.Perusahaan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.TabAdapter;

public class UbahHargaFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TabUbahHargaFragment ubahHarga;
    private TabDaftarHargaFragment daftarHarga;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public UbahHargaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        ((HomePerusahaan) getActivity()).setActionBarTitle("Ubah Harga");
        View view = inflater.inflate(R.layout.fragment_perusahaan_ubah_harga, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_ubah_harga);
        viewPager = (ViewPager) view.findViewById(R.id.pager_ubah_harga);
        ubahHarga = new TabUbahHargaFragment();
        daftarHarga = new TabDaftarHargaFragment();

        TabAdapter ubahHargaAdapter = new TabAdapter(getChildFragmentManager());
        ubahHargaAdapter.tambahFragment(daftarHarga, "Daftar Harga");
        ubahHargaAdapter.tambahFragment(ubahHarga, "Tambah Produk");

        viewPager.setAdapter(ubahHargaAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
