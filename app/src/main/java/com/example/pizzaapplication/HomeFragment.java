package com.example.pizzaapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzaapplication.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCustomerLoginHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.CustomerLogin();
            }
        });

        binding.buttonCompanyLoginHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.CompanyLogin();
            }
        });

        binding.buttonCompanyRegisterHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.CompanyCreateAccount();
            }
        });

        binding.buttonCustomerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.CustomerCreateAccount();
            }
        });


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (HomeListener) context;
    }

    HomeListener mListener;

    interface HomeListener{
        void CompanyLogin();
        void CustomerLogin();
        void CustomerCreateAccount();
        void CompanyCreateAccount();


    }
}