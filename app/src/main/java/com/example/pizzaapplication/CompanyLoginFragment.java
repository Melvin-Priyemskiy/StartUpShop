package com.example.pizzaapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pizzaapplication.databinding.FragmentCompanyLoginBinding;
import com.example.pizzaapplication.databinding.FragmentHomeBinding;


public class CompanyLoginFragment extends Fragment {

    public CompanyLoginFragment() {
        // Required empty public constructor
    }

    FragmentCompanyLoginBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        binding = FragmentCompanyLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonGoToHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.GoBackHomeFragment();
            }
        });

        binding.buttonCompanyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mListener.CompanyLoginSuccessful();
            }
        });

        binding.buttonCompanyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when the login is successful then go to the company dashboard

                String companyName = binding.editTextCompanyNameLogin.getText().toString();
                String password = binding.editTextCompanyLoginPassword.getText().toString();
                if(MainActivity.companies.size() != 0){
                    String p = MainActivity.companies.get(0).getCompanyName();
                    Log.d("TAG", "Index 0 employer company name: " + p);
                }
                Log.d("TAG", "login entered company name: " + companyName);
                Log.d("TAG", "login entered password: " + password);

                if(password.isBlank() || companyName.isBlank()){
                    Toast.makeText(getActivity(), "please fill out the fields", Toast.LENGTH_LONG).show();
                }
                else{
                    int x = MainActivity.companies.size();
                    boolean loginSuccess = false;
                    for (int i = 0; i < x; i++) {
                        String matchingName = MainActivity.companies.get(i).getCompanyName();
                        String matchPass = MainActivity.companies.get(i).getPassword();
                        if(matchingName.compareToIgnoreCase(companyName) == 0 && matchPass.compareTo(password) ==0){
                            MainActivity.currentCompany = MainActivity.companies.get(i);
                            loginSuccess = true;
                            mListener.CompanyLoginSuccessful();
                        }
                    }
                    if(loginSuccess){
                        Log.d("TAG", "Current Company: " + MainActivity.currentCompany);
                        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getActivity(), "Login Not Successful", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    CompanyLoginListener mListener;
    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mListener = (CompanyLoginListener) context;
    }

    interface CompanyLoginListener
    {
        void CompanyLoginSuccessful();
        void GoBackHomeFragment();
    }
}