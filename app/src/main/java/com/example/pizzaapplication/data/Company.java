package com.example.pizzaapplication.data;

import java.util.ArrayList;

public class Company {


    private String CompanyName;
    private String password;
    private ArrayList<PizzaItem> menu;
    private ArrayList<Request> requests;

    public Company(String companyName, String password, ArrayList<PizzaItem> menu, ArrayList<Request> requests) {
        CompanyName = companyName;
        this.password = password;
        this.menu = menu;
        this.requests = requests;
    }

    public Company() {
    }

    public ArrayList<PizzaItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<PizzaItem> menu) {
        this.menu = menu;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Company{" +
                "CompanyName='" + CompanyName + '\'' +
                ", password='" + password + '\'' +
                ", menu=" + menu +
                ", requests=" + requests +
                '}';
    }
}
