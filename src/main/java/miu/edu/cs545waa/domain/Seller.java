package miu.edu.cs545waa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("seller")
public class Seller extends User{

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "seller")
    private List<Product>products =new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<Order> orders = new ArrayList<>();

    public Seller() {}
    public Seller(String firstName,String lastName,String email,String password, boolean enabled) {
        super(firstName, lastName, email, password, enabled);
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

    public void addProduct(Product product){
        product.setSeller(this);
        products.add(product);
    }

    public void removeProduct(Product product){
        product.setSeller(null);
        products.remove(product);
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }
}