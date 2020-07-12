package miu.edu.cs545waa.domain;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
//@DiscriminatorValue("buyer")
public class Buyer extends User {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Seller> following;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Buyer() {
        this.following = new ArrayList<>();
    }

    public Buyer(String firstName, String lastName, String email, String password, boolean enabled) {
        super(firstName, lastName, email, password, enabled);
        this.following = new ArrayList<>();
    }

    public void addFollowing(Seller seller) {
        following.add(seller);
    }

    public void removeFollowing(Seller seller) {
        following.remove(seller);
    }

}
