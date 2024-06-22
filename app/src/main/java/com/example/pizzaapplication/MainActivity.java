package com.example.pizzaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pizzaapplication.data.Company;
import com.example.pizzaapplication.data.Customer;
import com.example.pizzaapplication.data.PizzaItem;
import com.example.pizzaapplication.data.Request;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HomeFragment.HomeListener,
        CompanyLoginFragment.CompanyLoginListener, CompanyRegisterFragment.CompanyRegisterListener,
        CustomerRegisterFragment.CustomerRegisterListener, CustomerLoginFragment.CustomerLoginListener,
        CompanyDashboardFragment.CompanyDashboardListener, CustomerDashboardFragment.CustomerDashboardListener,
        CompanyMenuFragment.MenuFragmentListener, UpdateMenuFragment.UpdatedMenuFragmentListener,
        CustomerOrderingFragment.CustomerOrderingListener, MenuItemFragment.MenuItemListener,
        ReviewOrderFragment.ReviewOrderListener, PreviousOrderFragment.PreviousOrderFragmentListener,
        AllCustomerOrdersFragment.AllCustomerOrdersListener
    {

    public static ArrayList<Company> companies = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static Company currentCompany = new Company();
    public static Customer currentCustomer = new Customer();

    public static PizzaItem menuItemUpdating = new PizzaItem();

    public static ArrayList<Request> allOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void CompanyLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CompanyLoginFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void CustomerLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CustomerLoginFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void CustomerCreateAccount() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CustomerRegisterFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void CompanyCreateAccount() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CompanyRegisterFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void CompanyLoginSuccessful() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CompanyDashboardFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void CompanyRegisterSuccessful()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CompanyLoginFragment())
                .addToBackStack(null)
                .commit();
    }

        @Override
        public void CustomerRegisterSuccessful() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new CustomerLoginFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void CustomerLoginSuccessful() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new CustomerDashboardFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void AddMenu() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new CompanyMenuFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
    public void GoBackHomeFragment()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }

        @Override
        public void CompanyMenuFragment() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new CustomerOrderingFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void CreatedOrders() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new PreviousOrderFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void CustomerOrders()
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new AllCustomerOrdersFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void GoToMenuUpdating() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new UpdateMenuFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void GoBackDashboard() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new CompanyDashboardFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void GoBackToCustomerDashboard() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new CustomerDashboardFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void GoToItem() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new MenuItemFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void ReviewOrder() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new ReviewOrderFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void GoBackToDashboard() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new CustomerDashboardFragment())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void GoBackToCompanyMenuCustomer() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootView, new CustomerOrderingFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }