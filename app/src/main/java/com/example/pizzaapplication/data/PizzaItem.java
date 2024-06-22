package com.example.pizzaapplication.data;

public class PizzaItem {
    private String title;
    private String toppings;
    private double cost;

    public PizzaItem(String title, String toppings, double cost) {
        this.title = title;
        this.toppings = toppings;
        this.cost = cost;
    }

    public PizzaItem() {
    }

    // Getters and setters for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getters and setters for toppings
    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    // Getters and setters for cost
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "PizzaItem{" +
                "title='" + title + '\'' +
                ", toppings='" + toppings + '\'' +
                ", cost=" + cost +
                '}';
    }
}
