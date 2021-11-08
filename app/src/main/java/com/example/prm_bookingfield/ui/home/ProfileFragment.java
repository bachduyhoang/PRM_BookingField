package com.example.prm_bookingfield.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_bookingfield.LoginActivity;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.databinding.FragmentHomeBinding;
import com.example.prm_bookingfield.dtos.User;
import com.example.prm_bookingfield.service.ManagePrefConfig;
import com.example.prm_bookingfield.service.UserService;

public class ProfileFragment extends Fragment {

    TextView userName;
    TextView phone;
    Button btnLogout;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        userName = view.findViewById(R.id.profile_Name);
        phone = view.findViewById(R.id.profile_PhoneNumber);
        btnLogout = view.findViewById(R.id.profile_btnLogout);

        UserService service = new UserService(getContext());
        service.getUserInformation(new UserService.VolleyResponseListener() {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onResponse(User user) {
                userName.setText(user.getFirstName());
                phone.setText(user.getPhone());
                btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ManagePrefConfig mng = new ManagePrefConfig(getActivity());
                        mng.userLogout();
                        Toast.makeText(getActivity(), "You are logged out successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                });
            }
        });
        return view;
    }

}