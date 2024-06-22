package com.example.pizzaapplication.data;

import java.util.ArrayList;

public class Request {
    private Customer customer;

    private Company company;

    private String reviewed;
    private ArrayList<PizzaItem> order;

    public Request(Customer customer, Company company, String reviewed, ArrayList<PizzaItem> order) {
        this.customer = customer;
        this.company = company;
        this.reviewed = reviewed;
        this.order = order;
    }

    public Request() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getReviewed() {
        return reviewed;
    }

    public void setReviewed(String reviewed) {
        this.reviewed = reviewed;
    }

    public ArrayList<PizzaItem> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<PizzaItem> order) {
        this.order = order;
    }
}
