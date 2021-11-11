package com.example.prm_bookingfield.ui.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.databinding.FragmentHomeBinding;
import com.example.prm_bookingfield.dtos.BookingAdapter;
import com.example.prm_bookingfield.dtos.BookingHistory;
import com.example.prm_bookingfield.dtos.User;
import com.example.prm_bookingfield.service.HistoryService;
import com.example.prm_bookingfield.service.UserService;
import com.example.prm_bookingfield.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private BookingAdapter bookingAdapter;
    private ArrayList<BookingHistory> listBooking;
    private RecyclerView history_rvListBooking;
    private TextView tvError;
//    public static HistoryFragment newInstance() {
//        return new HistoryFragment();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_layout, container, false);

        history_rvListBooking = view.findViewById(R.id.history_rvListBooking);

        HistoryService service = new HistoryService(getActivity());
        tvError = view.findViewById(R.id.tvError);
        UserService userService = new UserService(getContext());
        userService.getUserInformation(new UserService.VolleyResponseListener() {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onResponse(User user) {
                String idUser = user.getUserId();
                service.getHistory(idUser, new HistoryService.VolleyResponseListener() {
                    @Override
                    public void onError(String msg) {
                        Log.e("Error: ", msg);
                    }

                    @Override
                    public void onResponse(List<BookingHistory> listResponse) {
                        if(listResponse.size() != 0) {
                            listBooking = (ArrayList<BookingHistory>) listResponse;
                            bookingAdapter = new BookingAdapter(getActivity(), listBooking);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                            history_rvListBooking.setLayoutManager(linearLayoutManager);
                            history_rvListBooking.setAdapter(bookingAdapter);
                        }else{
                            tvError.setText("Nothing to show!");
                        }
                    }
                });
            }
        });
        return view;
    }

}