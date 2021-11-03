package com.example.prm_bookingfield.ui.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.adapters.PageHistoryAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private View view;

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab, container, false);

        tabLayout = view.findViewById(R.id.tabBar);
        viewPager = view.findViewById(R.id.viewPager);
        try {
            PageHistoryAdapter pageHistoryAdapter = new PageHistoryAdapter(this.getActivity());

            viewPager.setAdapter(pageHistoryAdapter);
            Bundle bundle = getArguments();
            if(bundle!=null){
                String action = bundle.getString("action");
                Log.d("Bundle", action);
                if (action!=null && action.equals("view_history")) {
                    viewPager.setCurrentItem(1,true);
                }
            }
            new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    if (position == 0) {
                        tab.setText("Past");
                    } else {
                        tab.setText("Now");
                    }
                }
            }).attach();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }
}