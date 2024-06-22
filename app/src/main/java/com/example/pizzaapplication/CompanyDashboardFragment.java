package com.example.pizzaapplication;

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

import com.example.pizzaapplication.data.Company;
import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.databinding.FragmentCompanyDashboardBinding;
import com.example.pizzaapplication.databinding.FragmentCompanyLoginBinding;
import com.example.pizzaapplication.databinding.MenuRowItemBinding;

import java.util.ArrayList;


public class CompanyDashboardFragment extends Fragment {

    public CompanyDashboardFragment() {
        // Required empty public constructor
    }

    FragmentCompanyDashboardBinding binding;
    ArrayList<PizzaItem> mMenuItems = MainActivity.currentCompany.getMenu();
    MenuAdapter adapter;

    class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>
    {
        @NonNull
        @Override
        public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MenuRowItemBinding itemBinding = MenuRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new MenuViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
            PizzaItem pizzaItem = mMenuItems.get(position);
            holder.setupUI(pizzaItem);
        }

        @Override
        public int getItemCount() {
            return mMenuItems.size();
        }

        class MenuViewHolder extends RecyclerView.ViewHolder {
            MenuRowItemBinding mBinding;
            PizzaItem mPizzaItem;

            public MenuViewHolder(MenuRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                mBinding = itemBinding;
            }

            public void setupUI(PizzaItem pizzaItem) {
                this.mPizzaItem = pizzaItem;
                mBinding.textViewMenuItemTitle.setText(mPizzaItem.getTitle());
                mBinding.textViewIngredients.setText(mPizzaItem.getToppings());
                String money = String.format("%.2f", mPizzaItem.getCost());

                mBinding.textViewPrice.setText("Cost: $" + money);

                mBinding.imageViewDelete.setVisibility(View.VISIBLE);

                mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mMenuItems.remove(position);
                            notifyItemRemoved(position);
                        }
                    }
                });

                mBinding.cardViewEvent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.menuItemUpdating = pizzaItem;
                        mListener.GoToMenuUpdating();
                    }
                });
            }
        }

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompanyDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Welcome " + MainActivity.currentCompany.getCompanyName() + "!");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MenuAdapter();
        binding.recyclerView.setAdapter(adapter);

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "company just logged out: " + MainActivity.currentCompany.toString());
                MainActivity.currentCompany = null;
                MainActivity.currentCustomer = null;
                MainActivity.menuItemUpdating = null;
                mListener.GoBackHomeFragment();
            }
        });

        binding.buttonGoToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.AddMenu();
            }
        });

        binding.buttonCustomerReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.CustomerOrders();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CompanyDashboardListener) context;
    }

    CompanyDashboardListener mListener;

    interface CompanyDashboardListener
    {
        void AddMenu();
        void GoBackHomeFragment();
        void CustomerOrders();
        void GoToMenuUpdating();
    }




}