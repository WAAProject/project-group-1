package miu.edu.cs545waa.domain;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User{
    private List<Product>products =new ArrayList<>();
    private List<Order>orders=new ArrayList<>();

    public Seller(String firstName,String lastName,String email,String password) {
        super(firstName, lastName, email, password);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}