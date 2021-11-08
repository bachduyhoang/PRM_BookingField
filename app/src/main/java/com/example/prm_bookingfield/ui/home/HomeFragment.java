package com.example.prm_bookingfield.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.databinding.FragmentHomeBinding;
import com.example.prm_bookingfield.dtos.GroupField;
import com.example.prm_bookingfield.dtos.MyAdapter;
import com.example.prm_bookingfield.service.GroupFieldService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private MyAdapter myAdapter;
    private ArrayList<GroupField> groupFieldArrayList;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView home_rvGroupField;
    private ImageButton fieldFive;
    private ImageButton fieldSeven;
    private ImageButton fieldEleven;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    private boolean clicked = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fieldFive = root.findViewById(R.id.home_ibFieldFive);
        fieldSeven = root.findViewById(R.id.home_ibFieldSeven);
        fieldEleven = root.findViewById(R.id.home_ibFieldEleven);

        //search
        fieldFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true;
                if(clicked){
                    int type = 5;
                    GroupFieldService service = new GroupFieldService(getActivity());
                    service.getGroupFieldByTypeField(type, new GroupFieldService.ListGroupFieldResponse(){
                        @Override
                        public void onError(String msg) {
                            Log.e("Error: ", msg);
                        }

                        @Override
                        public void onResponse(List<GroupField> listResponse) {
                            groupFieldArrayList = (ArrayList<GroupField>) listResponse;
                            myAdapter = new MyAdapter(getActivity(), (ArrayList<GroupField>) groupFieldArrayList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
                            home_rvGroupField.setLayoutManager(linearLayoutManager);
                            home_rvGroupField.setAdapter(myAdapter);
                        }
                    });
                }
            }
        });

        fieldSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true;
                if(clicked){
                    int type = 7;
                    GroupFieldService service = new GroupFieldService(getActivity());
                    service.getGroupFieldByTypeField(type, new GroupFieldService.ListGroupFieldResponse(){
                        @Override
                        public void onError(String msg) {
                            Log.e("Error: ", msg);
                        }

                        @Override
                        public void onResponse(List<GroupField> listResponse) {
                            groupFieldArrayList = (ArrayList<GroupField>) listResponse;
                            myAdapter = new MyAdapter(getActivity(), (ArrayList<GroupField>) groupFieldArrayList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
                            home_rvGroupField.setLayoutManager(linearLayoutManager);
                            home_rvGroupField.setAdapter(myAdapter);
                        }
                    });
                }
            }
        });

        fieldEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true;
                if(clicked){
                    int type = 11;
                    GroupFieldService service = new GroupFieldService(getActivity());
                    service.getGroupFieldByTypeField(type, new GroupFieldService.ListGroupFieldResponse(){
                        @Override
                        public void onError(String msg) {
                            Log.e("Error: ", msg);
                        }

                        @Override
                        public void onResponse(List<GroupField> listResponse) {
                            groupFieldArrayList = (ArrayList<GroupField>) listResponse;
                            myAdapter = new MyAdapter(getActivity(), (ArrayList<GroupField>) groupFieldArrayList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
                            home_rvGroupField.setLayoutManager(linearLayoutManager);
                            home_rvGroupField.setAdapter(myAdapter);
                        }
                    });
                }
            }
        });

        home_rvGroupField = root.findViewById(R.id.home_listGroupField);

        GroupFieldService service = new GroupFieldService(getActivity());
        service.getListGroupField(new GroupFieldService.ListGroupFieldResponse(){
            @Override
            public void onError(String msg) {
                Log.e("Error: ", msg);
            }

            @Override
            public void onResponse(List<GroupField> listResponse) {
                groupFieldArrayList = (ArrayList<GroupField>) listResponse;
                myAdapter = new MyAdapter(getActivity(), (ArrayList<GroupField>) groupFieldArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
                home_rvGroupField.setLayoutManager(linearLayoutManager);
                home_rvGroupField.setAdapter(myAdapter);
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}