package com.example.pizzaapplication;

import static com.example.pizzaapplication.MainActivity.allOrders;
import static com.example.pizzaapplication.MainActivity.currentCustomer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.data.Request;
import com.example.pizzaapplication.databinding.FragmentCustomerOrderingBinding;
import com.example.pizzaapplication.databinding.FragmentPreviousOrderBinding;
import com.example.pizzaapplication.databinding.RequestRowItemBinding;

import java.util.ArrayList;

public class PreviousOrderFragment extends Fragment {

    ArrayList<Request> mPizzaItems = new ArrayList<>();
    PreviousOrder adapter;

    class PreviousOrder extends RecyclerView.Adapter<PreviousOrder.PreviousOrderViewHolder>{

        @NonNull
        @Override
        public PreviousOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RequestRowItemBinding itemBinding = RequestRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new PreviousOrderViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull PreviousOrderViewHolder holder, int position) {
            Request request = mPizzaItems.get(position);
            holder.setupUI(request);
        }

        @Override
        public int getItemCount() {
            return mPizzaItems.size();
        }

        class PreviousOrderViewHolder extends RecyclerView.ViewHolder {
            RequestRowItemBinding mBinding;
            Request mRequest;
            public PreviousOrderViewHolder(RequestRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                mBinding = itemBinding;
            }

            @SuppressLint("SetTextI18n")
            public void setupUI(Request request) {
                this.mRequest = request;
                mBinding.textViewCompanyName.setText("Company Name: " + mRequest.getCompany().getCompanyName());
                ArrayList<PizzaItem> orderFromCompany = mRequest.getOrder();
                String allItemsOrdered = "";
                double totalPrice = 0;

                for (int i = 0; i < orderFromCompany.size(); i++) {
                    allItemsOrdered += orderFromCompany.get(i).getTitle();
                    totalPrice += orderFromCompany.get(i).getCost();

                    if (i < orderFromCompany.size() - 1) {
                        allItemsOrdered += ", ";
                    }
                }
                mBinding.textViewStatus.setText("Status: " + mRequest.getReviewed());
                mBinding.textViewAllItemsOrdered.setText("Items Ordered: " + allItemsOrdered);
                String money = String.format("%.2f", totalPrice);
                mBinding.textViewTotalCostOfItems.setText("Total Cost: $" + money);
            }
        }
    }

    public PreviousOrderFragment() {
        // Required empty public constructor
    }

    FragmentPreviousOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPreviousOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Filter the orders for the current customer
        filterOrdersForCurrentCustomer();

        binding.recyclerViewPreviousOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PreviousOrder();
        binding.recyclerViewPreviousOrder.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.buttonGoBackToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.GoBackToCustomerDashboard();
            }
        });
    }

    // New method to filter orders for the current customer
    private void filterOrdersForCurrentCustomer() {
        String matchingName = currentCustomer.getUsername();
        String matchPass = currentCustomer.getPassword();

        for (Request request : allOrders) {
            if (matchingName.compareToIgnoreCase(request.getCustomer().getUsername()) == 0 &&
                    matchPass.compareTo(request.getCustomer().getPassword()) == 0) {
                mPizzaItems.add(request);
            }
        }
    }

    PreviousOrderFragmentListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (PreviousOrderFragmentListener) context;
    }

    interface PreviousOrderFragmentListener {
        void GoBackToCustomerDashboard();
    }
}
