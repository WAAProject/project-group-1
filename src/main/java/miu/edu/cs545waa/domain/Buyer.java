package miu.edu.cs545waa.domain;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
//@DiscriminatorValue("buyer")
public class Buyer extends User {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Seller> following = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();



    public Buyer() {
    }
    public Buyer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
}
