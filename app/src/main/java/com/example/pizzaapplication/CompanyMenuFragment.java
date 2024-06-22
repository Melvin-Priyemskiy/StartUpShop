package com.example.pizzaapplication;

import static com.example.pizzaapplication.MainActivity.currentCompany;

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
import com.example.pizzaapplication.databinding.FragmentCompanyMenuBinding;
import com.example.pizzaapplication.databinding.FragmentCustomerLoginBinding;

import java.util.ArrayList;


public class CompanyMenuFragment extends Fragment {


    public CompanyMenuFragment() {
        // Required empty public constructor
    }


    FragmentCompanyMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompanyMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonGoBackDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.GoBackDashboard();
            }
        });



        binding.buttonSubmitMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menuItemName = binding.editTextMenuItemName.getText().toString().trim();
                String menuIngredients = binding.editTextIngredientList.getText().toString().trim();

                if (menuItemName.isEmpty() || menuIngredients.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter both item name and ingredients", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    String priceInput = binding.editTextTextMenuPrice.getText().toString().trim();
                    double menuPrice = Double.parseDouble(priceInput);

                    // Check if the price has more than 2 decimal places
                    int decimalIndex = priceInput.indexOf(".");
                    if (decimalIndex != -1 && priceInput.length() - decimalIndex - 1 > 2) {
                        Toast.makeText(getActivity(), "Price can only have up to two decimal places", Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Create a new PizzaItem instance
                    PizzaItem pizzaItem = new PizzaItem(menuItemName, menuIngredients, menuPrice);
//
//                    Company currentCompany = MainActivity.currentCompany;
//
//                    Log.d("TAG", "Company Details: " + currentCompany);
//
//                    currentCompany.getMenu().add(justAddedMenuItem);
//
//
//                    ArrayList<PizzaItem> prevMenu = MainActivity.currentCompany.getMenu();
//
//                    for (int i = 0; i < prevMenu.size(); i++) {
//                        Log.d("TAG", "Prev Menu Items #" + (i+1) + ": " + prevMenu.get(i));
//                    }
//
//                    prevMenu.add(justAddedMenuItem);
//
//                    for (int i = 0; i < prevMenu.size(); i++) {
//                        Log.d("TAG", "Updated Menu Items #" + (i+1) + ": " + prevMenu.get(i));
//                    }

                    MainActivity.currentCompany.getMenu().add(pizzaItem);
                    Toast.makeText(getActivity(), "Item Successfully added", Toast.LENGTH_LONG).show();
                    mListener.GoBackDashboard();


                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Please enter a valid price", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    MenuFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (MenuFragmentListener) context;
    }

    interface MenuFragmentListener{
        void GoBackDashboard();
    }
}