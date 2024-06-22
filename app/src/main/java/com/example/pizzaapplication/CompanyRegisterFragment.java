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

import com.example.pizzaapplication.data.Company;
import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.data.Request;
import com.example.pizzaapplication.databinding.FragmentCompanyRegisterBinding;
import com.example.pizzaapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class CompanyRegisterFragment extends Fragment {


    public CompanyRegisterFragment() {
        // Required empty public constructor
    }

    FragmentCompanyRegisterBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompanyRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.GoBackHomeFragment();
            }
        });

        binding.buttonCompanyReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String companyName = binding.editTextCompanyReg.getText().toString();
                String password = binding.editTextCompanyRegPassword.getText().toString();
                ArrayList<Request> requests = new ArrayList<>();
                ArrayList<PizzaItem> menu = new ArrayList<>();
                Log.d("TAG", "onClick: this is the password " + password);
                Log.d("TAG", "onClick: this is the companyName " + companyName);
                if(password.isBlank() || companyName.isBlank()){
                    Toast.makeText(getActivity(), "please fill out the fields", Toast.LENGTH_LONG).show();
                }
                else{
                    //Make sure employername is unique
                    int x = MainActivity.companies.size();
                    boolean uniqueCompany = true;
                    for (int i = 0; i < x; i++) {
                        if(companyName.compareToIgnoreCase(MainActivity.companies.get(i).getCompanyName()) == 0){
                            uniqueCompany = false;

                        }
                    }
                    if(uniqueCompany)
                    {
                        Company addingCompany = new Company(companyName, password,menu, requests);
                        MainActivity.companies.add(addingCompany);
                        Toast.makeText(getActivity(), "Company Account Created!", Toast.LENGTH_LONG).show();
                        //when the employer information is correct then go to the dashboard
                        mListener.CompanyRegisterSuccessful();
                    }
                    else{
                        Toast.makeText(getActivity(), "Company name is not unique", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    CompanyRegisterListener mListener;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mListener = (CompanyRegisterListener) context;
    }

    interface CompanyRegisterListener
    {
        void CompanyRegisterSuccessful();
        void GoBackHomeFragment();
    }
}