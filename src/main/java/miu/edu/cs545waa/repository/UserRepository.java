package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.Order;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    @Query(value = "SELECT s FROM User s WHERE s.type = ?1")
    List<User> findByType(String typeValue);

    @Query(value = "SELECT b FROM Buyer b JOIN b.following s WHERE b.id = :buyerId AND s.id = :sellerId")
    Buyer isFollowing(Long buyerId, Long sellerId);

    @Query(value = "SELECT b FROM Buyer b JOIN b.following s WHERE s.id = :sellerId")
    List<Buyer> getFollowersNumber(Long sellerId);

    // Find orders by Seller
    @Query(value = "SELECT s.orders FROM Seller s WHERE s = :seller")
    List<Order> getOrdersBySeller(Seller seller);

    @Query(value = "SELECT distinct o FROM Buyer b JOIN b.orders o WHERE b = :buyer ORDER BY o.updateTime DESC ")
    List<Order> getOrdersByBuyer(Buyer buyer);
}
