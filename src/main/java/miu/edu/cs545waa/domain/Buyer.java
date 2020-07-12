package miu.edu.cs545waa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Buyer extends User {

    @ManyToMany
    @JoinColumn(name = "owner_id")
    private List<Seller> following = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public Buyer() {
    }

    public Buyer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
}
