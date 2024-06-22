package com.example.pizzaapplication;

import static com.example.pizzaapplication.MainActivity.currentCompany;
import static com.example.pizzaapplication.MainActivity.currentCustomer;

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

import com.example.pizzaapplication.data.Company;
import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.data.Request;
import com.example.pizzaapplication.databinding.CompanyRowItemBinding;
import com.example.pizzaapplication.databinding.FragmentCompanyDashboardBinding;
import com.example.pizzaapplication.databinding.FragmentCustomerDashboardBinding;

import java.util.ArrayList;

public class CustomerDashboardFragment extends Fragment {

    ArrayList<Company> mCompanies = MainActivity.companies;

    CompanyAdapter adapter;


    public CustomerDashboardFragment() {
        // Required empty public constructor
    }

    FragmentCustomerDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomerDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "customer just logged out:  " + MainActivity.currentCustomer.toString());
                MainActivity.currentCompany = null;
                MainActivity.currentCustomer = null;
                MainActivity.menuItemUpdating = null;
                mListener.GoBackHomeFragment();
            }
        });

        binding.buttonCreatedOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.CreatedOrders();
            }
        });

        getActivity().setTitle("Welcome " + currentCustomer.getUsername() +"!");
        binding.recyclerViewCustomer.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CompanyAdapter();
        binding.recyclerViewCustomer.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CustomerDashboardListener) context;
    }

    CustomerDashboardListener mListener;
    interface CustomerDashboardListener{
        void GoBackHomeFragment();
        void CompanyMenuFragment();
        void CreatedOrders();
    }

    class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>
    {
        @NonNull
        @Override
        public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CompanyRowItemBinding itemBinding = CompanyRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new CompanyViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
            Company company = mCompanies.get(position);
            holder.setupUI(company);
        }

        @Override
        public int getItemCount() {
            return mCompanies.size();
        }

        class CompanyViewHolder extends RecyclerView.ViewHolder
        {
            CompanyRowItemBinding mBinding;
            Company mCompany;
            public CompanyViewHolder(CompanyRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                mBinding = itemBinding;
            }
            public void setupUI(Company company){
                this.mCompany = company;
                mBinding.textViewCompanyNameInCustDash.setText(mCompany.getCompanyName());
                mBinding.textViewAmount.setText("Amount of Items: " + mCompany.getMenu().size());


                mBinding.cardViewEvent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currentCompany = company;
                        ArrayList<PizzaItem> request = new ArrayList<>();
                        Request newRequest = new Request(currentCustomer, currentCompany,"No", request);
                        currentCustomer.setCurrentOrder(newRequest);
                        mListener.CompanyMenuFragment();
                    }
                });

            }
        }
    }
}