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

import com.example.pizzaapplication.data.Customer;
import com.example.pizzaapplication.databinding.FragmentCompanyRegisterBinding;
import com.example.pizzaapplication.databinding.FragmentCustomerLoginBinding;


public class CustomerLoginFragment extends Fragment {


    public CustomerLoginFragment() {
        // Required empty public constructor
    }

    FragmentCustomerLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        binding = FragmentCustomerLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonGoHomeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.GoBackHomeFragment();
            }
        });


        binding.buttonCustomerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when the login is successful then go to the company dashboard

                String customerName = binding.editTextCustomerLoginEmail.getText().toString();
                String password = binding.editTextCustomerLoginPassword.getText().toString();
                Log.d("TAG", "login entered company name: " + customerName);
                Log.d("TAG", "login entered password: " + password);

                if(MainActivity.customers.size() != 0){
                    String p = MainActivity.customers.get(0).getUsername();
                    Log.d("TAG", "Index 0 employer company name: " + p);
                }

                if(password.isBlank() || customerName.isBlank()){
                    Toast.makeText(getActivity(), "please fill out the fields", Toast.LENGTH_LONG).show();
                }
                else{
                    int x = MainActivity.customers.size();
                    boolean loginSuccess = false;
                    for (int i = 0; i < x; i++) {
                        String matchingName = MainActivity.customers.get(i).getUsername();
                        String matchPass = MainActivity.customers.get(i).getPassword();
                        if(matchingName.compareToIgnoreCase(customerName) == 0 && matchPass.compareTo(password) ==0){
                            MainActivity.currentCustomer = MainActivity.customers.get(i);
                            loginSuccess = true;
                            mListener.CustomerLoginSuccessful();
                        }
                    }
                    if(loginSuccess){
                        Log.d("TAG", "Current Customer: " + MainActivity.currentCustomer);
                        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getActivity(), "Login Not Successful", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    CustomerLoginListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CustomerLoginListener) context;
    }

    interface CustomerLoginListener
    {
        void CustomerLoginSuccessful();
        void GoBackHomeFragment();
    }
}