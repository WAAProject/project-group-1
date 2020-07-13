package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.Order;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;

import java.util.List;

public interface UserService {

    public User findByEmail(String email);
    public List<User> getAll();
    public List<User> findByType(String type);
    public User findById(Long id);
    public User save(User user);
//    public User find(Long id);
    public Buyer getAuthenticatedBuyer();
    public User getAuthenticatedUser();
    List<Order> getOrdersBySeller(Seller seller);
    List<Order> getOrdersByBuyer();
    public List<Buyer> getFollewersNumber(Long sellerId);
    Boolean buyerHasCoupon();
    public Seller addFollower(Long sellerId, String action);
    public boolean isFollowing(Long sellerId);

}
