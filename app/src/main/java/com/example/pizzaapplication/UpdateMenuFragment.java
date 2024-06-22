package com.example.pizzaapplication;

import static com.example.pizzaapplication.MainActivity.menuItemUpdating;

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

import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.databinding.FragmentHomeBinding;
import com.example.pizzaapplication.databinding.FragmentUpdateMenuBinding;


public class UpdateMenuFragment extends Fragment {



    public UpdateMenuFragment() {
        // Required empty public constructor
    }

    FragmentUpdateMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.textViewUpdate.setText("Updating: "+menuItemUpdating.getTitle());

        binding.buttonSubmitMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menuItemName = binding.editTextUpdateMenuItem.getText().toString();
                String menuIngredients = binding.editTextUpdateMenuIngredients.getText().toString();

                try
                {
                    String priceInput = binding.editTextUpdatingMenuPrice.getText().toString();
                    double menuPrice = Double.parseDouble(priceInput);

                    // Check if the price has more than 2 decimal places
                    int decimalIndex = priceInput.indexOf(".");
                    if (decimalIndex != -1 && priceInput.length() - decimalIndex - 1 > 2)
                    {
                        Toast.makeText(getActivity(), "Price can only have up to two decimal places", Toast.LENGTH_LONG).show();
                        return;
                    }

                    PizzaItem pizzaItem = new PizzaItem(menuItemName, menuIngredients, menuPrice);
                    int index = MainActivity.currentCompany.getMenu().indexOf(menuItemUpdating);
                    MainActivity.currentCompany.getMenu().set(index, pizzaItem);


                    Log.d("TAG", "Item updated: " + pizzaItem );
                    Toast.makeText(getActivity(), "Item Successfully updated:" , Toast.LENGTH_LONG).show();
                    mListener.GoBackDashboard();
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getActivity(), "Please enter a valid price", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.buttonGoBackDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.GoBackDashboard();
            }
        });
    }
    UpdatedMenuFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UpdatedMenuFragmentListener) context;
    }

    interface UpdatedMenuFragmentListener
    {
        void GoBackDashboard();
    }}