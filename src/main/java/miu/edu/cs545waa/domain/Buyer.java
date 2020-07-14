package miu.edu.cs545waa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("buyer")
public class Buyer extends User {

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Seller> following = new ArrayList<Seller>();

    @JoinColumn(name = "owner_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
    private List<Order> orders = new ArrayList<>();

    private int coupon = 0;

    public Buyer() {
    }

    public Buyer(String firstName, String lastName, String email, String password, boolean enabled) {
        super(firstName, lastName, email, password, enabled);
    }

    public List<Seller> getFollowing() {
        return following;
    }

    public void setFollowing(List<Seller> following) {
        this.following = following;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }

    public void addFollowing(Seller seller){
        following.add(seller);
    }

    public void removeFollowing(Seller seller){
        following.remove(seller);
    }

    public void removeOrderItem(OrderItem item) {
        orderItems.remove(item);
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }
}
