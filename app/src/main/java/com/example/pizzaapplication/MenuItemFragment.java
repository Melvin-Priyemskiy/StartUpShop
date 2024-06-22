package com.example.pizzaapplication;

import static com.example.pizzaapplication.MainActivity.currentCompany;
import static com.example.pizzaapplication.MainActivity.currentCustomer;
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
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.data.Request;
import com.example.pizzaapplication.databinding.FragmentCustomerOrderingBinding;
import com.example.pizzaapplication.databinding.FragmentMenuItemBinding;

import java.util.ArrayList;


public class MenuItemFragment extends Fragment {

    public MenuItemFragment() {
        // Required empty public constructor
    }

    FragmentMenuItemBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        binding = FragmentMenuItemBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        String itemName = menuItemUpdating.getTitle();
        String ingredientList = menuItemUpdating.getToppings();
        double singleUnitPrice = menuItemUpdating.getCost();

        binding.textViewRealItemName.setText(itemName);
        binding.textViewRealIIngredients.setText(ingredientList);

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.textViewProgress.setText(""+i);

                double totalCost = i * singleUnitPrice;
                String money = String.format("%.2f", totalCost);
                binding.textViewMathTotalQuantityCost.setText("$"+money);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.buttonGoBackTOMenuCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItemUpdating = null;
                mListener.GoBackToCompanyMenuCustomer();
            }
        });

        binding.buttonSubmitQuantityToShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = Integer.parseInt(binding.textViewProgress.getText().toString());
                if(quantity != 0)
                {
                    ArrayList<PizzaItem> customerOrder = currentCustomer.getCurrentOrder().getOrder();

                    for (int i = 0; i < quantity ; i++)
                    {
                        PizzaItem newItem = new PizzaItem(itemName, ingredientList, singleUnitPrice);
                        customerOrder.add(newItem);
                    }

                    menuItemUpdating = null;
                    Log.d("TAG", "Updated Customer Order: " + customerOrder);
                    Toast.makeText(getActivity(), "Shopping Cart Updated", Toast.LENGTH_LONG).show();
                    mListener.GoBackToCompanyMenuCustomer();
                }
                else{
                    Toast.makeText(getActivity(), "Please add a quantity to your item", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    MenuItemListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (MenuItemListener) context;
    }

    interface MenuItemListener{
        void GoBackToCompanyMenuCustomer();
    }
}