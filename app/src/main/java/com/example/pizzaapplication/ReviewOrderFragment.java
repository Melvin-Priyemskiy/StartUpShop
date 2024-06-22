package com.example.pizzaapplication;

import static com.example.pizzaapplication.MainActivity.allOrders;
import static com.example.pizzaapplication.MainActivity.currentCompany;
import static com.example.pizzaapplication.MainActivity.currentCustomer;
import static com.example.pizzaapplication.MainActivity.menuItemUpdating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.databinding.FragmentCompanyRegisterBinding;
import com.example.pizzaapplication.databinding.FragmentReviewOrderBinding;
import com.example.pizzaapplication.databinding.MenuRowItemBinding;

import java.util.ArrayList;


public class ReviewOrderFragment extends Fragment {


    ArrayList<PizzaItem> mOrder = currentCustomer.getCurrentOrder().getOrder();
    CustomerOrderAdapter adapter;
    class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.CustomerOrderViewHolder>{

        @NonNull
        @Override
        public CustomerOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MenuRowItemBinding itemBinding = MenuRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new CustomerOrderViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomerOrderViewHolder holder, int position) {
            PizzaItem pizzaItem = mOrder.get(position);
            holder.setupUI(pizzaItem);
        }

        @Override
        public int getItemCount() {
            return mOrder.size();
        }

        class CustomerOrderViewHolder extends RecyclerView.ViewHolder{
            MenuRowItemBinding mBinding;
            PizzaItem item;
            public CustomerOrderViewHolder(MenuRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                mBinding = itemBinding;
            }

            @SuppressLint("SetTextI18n")
            public void setupUI(PizzaItem pizzaItem){
                this.item = pizzaItem;
                mBinding.textViewMenuItemTitle.setText(item.getTitle());
                mBinding.textViewIngredients.setText(item.getToppings());

                String money = String.format("%.2f", item.getCost());

                mBinding.textViewPrice.setText("Cost: $" + money);

                mBinding.imageViewDelete.setVisibility(View.VISIBLE);

                mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOrder.remove(item);
                        adapter.notifyDataSetChanged();
                    }
                });

//                mBinding.imageViewInfo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        item.setReviewed("Accepted");
//                        item.remove(item);
//                        double total = MainActivity.currentEmployer.getTotalAmountPaid();
//                        boolean employeeExists = false;
//
//                        int x = MainActivity.currentEmployer.getEmployees().size();
//                        for (int i = 0; i < x; i++) {
//                            if(MainActivity.currentEmployer.getEmployees().get(i) == item.getEmployee()){
//                                employeeExists = true;
//                            }
//                        }
//
//                        if(employeeExists)
//                        {
//                            total = total + item.getHoursRequested().getTotalMoneyRequested();
//                            MainActivity.currentEmployer.setTotalAmountPaid(total);
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//                });


            }

        }
    }


    public ReviewOrderFragment() {
        // Required empty public constructor
    }

    FragmentReviewOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        binding = FragmentReviewOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewRevewingOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CustomerOrderAdapter();
        binding.recyclerViewRevewingOrder.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.button2GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.GoBackToCompanyMenuCustomer();
            }
        });

        binding.buttonSubmitOrderFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(mOrder.size() != 0)
                {

                    currentCustomer.getPreviousOrders().add(currentCustomer.getCurrentOrder());
                    allOrders.add(currentCustomer.getCurrentOrder());

                    Log.d("TAG", "Updated Prev Orders: " + currentCustomer.getPreviousOrders());
                    Toast.makeText(getActivity(), "Order Submitted", Toast.LENGTH_LONG).show();
                    currentCustomer.setCurrentOrder(null);
                    currentCompany = null;
                    menuItemUpdating = null;
                    mListener.GoBackToDashboard();
                }
                else{
                    Toast.makeText(getActivity(), "Please add items to your shopping cart first", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    ReviewOrderListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ReviewOrderListener) context;
    }

    interface ReviewOrderListener
    {
        void GoBackToDashboard();
        void GoBackToCompanyMenuCustomer();
    }
}