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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.data.Request;
import com.example.pizzaapplication.databinding.FragmentCompanyLoginBinding;
import com.example.pizzaapplication.databinding.FragmentCustomerOrderingBinding;
import com.example.pizzaapplication.databinding.MenuRowItemBinding;

import java.util.ArrayList;


public class CustomerOrderingFragment extends Fragment {


    OrderingAdapter adapter;
    ArrayList<PizzaItem> mMenu = MainActivity.currentCompany.getMenu();


    class OrderingAdapter extends RecyclerView.Adapter<OrderingAdapter.OrderingViewHolder>
    {
        @NonNull
        @Override
        public OrderingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MenuRowItemBinding itemBinding = MenuRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new OrderingViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderingViewHolder holder, int position) {
            PizzaItem employee = mMenu.get(position);
            holder.setupUI(employee);
        }

        @Override
        public int getItemCount() {
            return mMenu.size();
        }

        class OrderingViewHolder extends RecyclerView.ViewHolder
        {
            MenuRowItemBinding mBinding;
            PizzaItem mMenu;
            public OrderingViewHolder(MenuRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                mBinding = itemBinding;
            }
            public void setupUI(PizzaItem pizzaItem) {
                this.mMenu = pizzaItem;
                mBinding.textViewMenuItemTitle.setText(mMenu.getTitle());
                mBinding.textViewIngredients.setText(mMenu.getToppings());
                String money = String.format("%.2f", mMenu.getCost());

                mBinding.textViewPrice.setText("Cost: $" + money);

                mBinding.imageViewDelete.setVisibility(View.INVISIBLE);

                mBinding.cardViewEvent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.menuItemUpdating = pizzaItem;
                        mListener.GoToItem();
                    }
                });
            }        }
    }


    public CustomerOrderingFragment() {
        // Required empty public constructor
    }

    FragmentCustomerOrderingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomerOrderingBinding.inflate(inflater, container, false);
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewOrderCustomer.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new OrderingAdapter();
        binding.recyclerViewOrderCustomer.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        binding.buttonReviewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.ReviewOrder();
            }
        });

        binding.buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCustomer.getCurrentOrder().setOrder(null);
                currentCompany = null;
                mListener.GoBackToCustomerDashboard();
            }
        });
    }

    CustomerOrderingListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CustomerOrderingListener) context;
    }

    interface CustomerOrderingListener{
        void GoBackToCustomerDashboard();
        void GoToItem();
        void ReviewOrder();
    }
}