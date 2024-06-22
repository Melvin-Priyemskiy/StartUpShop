package com.example.pizzaapplication.data;

import java.util.ArrayList;

public class Customer {
    private String username;
    private String password;
    private ArrayList<Request> previousOrders;
    private Request currentOrder;

    public Customer(String username, String password, ArrayList<Request> previousOrders, Request currentOrder) {
        this.username = username;
        this.password = password;
        this.previousOrders = previousOrders;
        this.currentOrder = currentOrder;
    }

    public Customer() {
    }

    public Request getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Request currentOrder) {
        this.currentOrder = currentOrder;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Request> getPreviousOrders() {
        return previousOrders;
    }

    public void setPreviousOrders(ArrayList<Request> previousOrders) {
        this.previousOrders = previousOrders;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", previousOrders=" + previousOrders +
                ", currentOrder=" + currentOrder +
                '}';
    }
}
