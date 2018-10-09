package id.ac.unej.ilkom.ods.buangin.View.Volunteer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.unej.ilkom.ods.buangin.Adapter.TabAdapter;
import id.ac.unej.ilkom.ods.buangin.R;

public class InfoPoinFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public InfoPoinFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeVolunteer) getActivity()).setActionBarTitle("Info Poin");

        View view = inflater.inflate(R.layout.fragment_volunteer_info_poin, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_infopoin);
        viewPager = (ViewPager) view.findViewById(R.id.pager_infopoin);

        TabAdapter infopoin_adapter = new TabAdapter(getChildFragmentManager());
        infopoin_adapter.tambahFragment(new TabPoinFragment(), "Poin");
        infopoin_adapter.tambahFragment(new TabVoucherFragment(), "Voucher");

//        infopoin_adapter.tambahFragment(((HomeVolunteer) getActivity()).getTabPoin(), "Poin");
//        infopoin_adapter.tambahFragment(((HomeVolunteer) getActivity()).getTabVoucher(), "Voucher");

        viewPager.setAdapter(infopoin_adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
