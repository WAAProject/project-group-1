package miu.edu.cs545waa.domain;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Seller seller;

    @ManyToOne(cascade = CascadeType.ALL)
    @Valid
    private Address BillingAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @Valid
    private Address ShippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private Payment payment;

    private double sum;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();

    public Order(){}
    public Order(Date orderDate, List<OrderItem> items, Buyer buyer, Seller seller) {
        this.setBuyer(buyer);
        this.setSeller(seller);
        this.orderDate = orderDate;
        this.status = OrderStatus.New;
        this.items = items;
        this.sum = 0.0;
        for(OrderItem item : this.items){
            this.sum += item.getPrice();
        }
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Address getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        BillingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
