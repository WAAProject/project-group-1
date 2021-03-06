package miu.edu.cs545waa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product product;

    @NotNull(message = "{Product.quantity.NotEmpty}")
    private int quantity;

    private double totalPrice;

    public OrderItem() {}

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = quantity * product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return totalPrice;
    }

    public void setPrice(double price) {
        this.totalPrice = price;
    }
}
