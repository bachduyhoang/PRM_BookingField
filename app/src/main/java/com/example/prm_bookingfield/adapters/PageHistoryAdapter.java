package com.example.prm_bookingfield.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.prm_bookingfield.ui.history.HistoryFragment;

public class PageHistoryAdapter extends FragmentStateAdapter {
    public PageHistoryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HistoryFragment();
            default:
                return new HistoryFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
