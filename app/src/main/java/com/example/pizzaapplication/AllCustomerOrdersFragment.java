package com.example.pizzaapplication;

import static com.example.pizzaapplication.MainActivity.allOrders;
import static com.example.pizzaapplication.MainActivity.currentCompany;
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
import com.example.pizzaapplication.databinding.CompanyRequestRowItemBinding;
import com.example.pizzaapplication.databinding.FragmentAllCustomerOrdersBinding;
import com.example.pizzaapplication.databinding.FragmentUpdateMenuBinding;

import java.util.ArrayList;


public class AllCustomerOrdersFragment extends Fragment {


    ArrayList<Request> mRequests = new ArrayList<>();
    AllCustomerOrder adapter;

    class AllCustomerOrder extends RecyclerView.Adapter<AllCustomerOrder.AllCustomerOrderViewHolder>{

        @NonNull
        @Override
        public AllCustomerOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CompanyRequestRowItemBinding itemBinding = CompanyRequestRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new AllCustomerOrderViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull AllCustomerOrderViewHolder holder, int position) {
            Request request = mRequests.get(position);
            holder.setupUI(request);
        }

        @Override
        public int getItemCount() {
            return mRequests.size();
        }

        class AllCustomerOrderViewHolder extends RecyclerView.ViewHolder{
            CompanyRequestRowItemBinding mBinding;
            Request mRequest;
            public AllCustomerOrderViewHolder(CompanyRequestRowItemBinding itemBinding)
            {
                super(itemBinding.getRoot());
                mBinding = itemBinding;
            }

            @SuppressLint("SetTextI18n")
            public void setupUI(Request request){
                this.mRequest = request;
                mBinding.textViewCompanyName.setText("Customer Name: " + mRequest.getCustomer().getUsername());
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
                mBinding.textviewStatusFromCompany.setText("Status: " + mRequest.getReviewed());
                mBinding.textViewCompanyToCustomerOrder.setText("Items Ordered: " + allItemsOrdered);
                String money = String.format("%.2f", totalPrice);
                mBinding.textViewTotalCostOfitemsFromCompany.setText("Total Cost: $" + money);



                mBinding.imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mRequest.setReviewed("Rejected");
                        adapter.notifyDataSetChanged();
                    }
                });

                mBinding.imageButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mRequest.setReviewed("Finished");
                        adapter.notifyDataSetChanged();
                    }
                });


            }

        }
    }



    public AllCustomerOrdersFragment() {
        // Required empty public constructor
    }

    FragmentAllCustomerOrdersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        binding = FragmentAllCustomerOrdersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        filterOrdersForCompany();


        binding.recyclerViewPreviousOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AllCustomerOrder();
        binding.recyclerViewPreviousOrder.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.buttonGoBackTodashboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             mListener.GoBackDashboard();
            }
        });
    }

    private void filterOrdersForCompany() {
        String matchingName = currentCompany.getCompanyName();
        String matchPass = currentCompany.getPassword();

        for (Request request : allOrders) {
            if (matchingName.compareToIgnoreCase(request.getCompany().getCompanyName()) == 0 &&
                    matchPass.compareTo(request.getCompany().getPassword()) == 0) {
                mRequests.add(request);
            }
        }
    }

    AllCustomerOrdersListener mListener;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mListener = (AllCustomerOrdersListener) context;
    }

    interface AllCustomerOrdersListener{
        void GoBackDashboard();
    }
}