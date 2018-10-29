package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.adapter.TabAdapter;

public class FragmentDaftarPengguna extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FloatingActionButton tambah;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftar_pengguna, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_daftar_pengguna);
        viewPager = (ViewPager) view.findViewById(R.id.pager_dafftar_pengguna);

        TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        adapter.tambahFragment(new FragmentVolunteer(), "Volunteer");
        adapter.tambahFragment(new FragmentBankSampah(), "Bank Sampah");
        adapter.tambahFragment(new FragmentMitra(), "Mitra UMKM");
        adapter.tambahFragment(new FragmentPerusahaan(), "Perusahaan");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tambah = (FloatingActionButton) view.findViewById(R.id.daftar_pengguna_btn_tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), DaftarPenggunaBaru.class));
            }
        });

        return view;
    }
}
