package miu.edu.cs545waa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends User{

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product>products =new ArrayList<>();

    @OneToMany
    private List<Order>orders=new ArrayList<>();

    public Seller() {}
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