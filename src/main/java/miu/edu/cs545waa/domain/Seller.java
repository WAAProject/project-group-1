package miu.edu.cs545waa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("SELLER")
public class Seller extends User {

    // disabling associations until figure out relationships
//    private List<Product> products;
//    private List<Order> orders;

    public Seller() {
        super();
    }

    public Seller(String firstName,String lastName,String email,String password) {
        super(firstName, lastName, email, password);
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
//
//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }
}