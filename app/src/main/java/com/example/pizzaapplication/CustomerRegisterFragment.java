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
import com.example.pizzaapplication.data.Customer;
import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.data.Request;
import com.example.pizzaapplication.databinding.FragmentCustomerLoginBinding;
import com.example.pizzaapplication.databinding.FragmentCustomerRegisterBinding;

import java.util.ArrayList;

public class CustomerRegisterFragment extends Fragment {

    public CustomerRegisterFragment() {
        // Required empty public constructor
    }


    FragmentCustomerRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomerRegisterBinding.inflate(inflater, container, false);
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

        binding.buttomCustomerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = binding.editTextCustomerRegEmail.getText().toString();
                String password = binding.editTextCustomerRegPassword.getText().toString();
                Request currentOrder = null;
                ArrayList<Request> orders = new ArrayList<>();
                Log.d("TAG", "onClick: this is the password " + password);
                Log.d("TAG", "onClick: this is the customerName " + customerName);
                if(password.isBlank() || customerName.isBlank()){
                    Toast.makeText(getActivity(), "please fill out the fields", Toast.LENGTH_LONG).show();
                }
                else{
                    //Make sure customer name is unique
                    int x = MainActivity.customers.size();
                    boolean uniqueCustomer = true;
                    for (int i = 0; i < x; i++) {
                        if(customerName.compareToIgnoreCase(MainActivity.customers.get(i).getUsername()) == 0){
                            uniqueCustomer = false;

                        }
                    }
                    if(uniqueCustomer)
                    {
                        Customer addingCustomer = new Customer(customerName, password,orders, currentOrder);
                        MainActivity.customers.add(addingCustomer);
                        Toast.makeText(getActivity(), "Customer Account Created!", Toast.LENGTH_LONG).show();
                        //when the employer information is correct then go to the dashboard
                        mListener.CustomerRegisterSuccessful();
                    }
                    else{
                        Toast.makeText(getActivity(), "Customer name is not unique", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CustomerRegisterListener) context;
    }
    CustomerRegisterListener mListener;
    interface CustomerRegisterListener{
        void CustomerRegisterSuccessful();
        void GoBackHomeFragment();
    }
}