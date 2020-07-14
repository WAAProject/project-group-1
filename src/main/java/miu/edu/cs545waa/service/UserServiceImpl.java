package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.Order;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import miu.edu.cs545waa.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findByType(String type) {
        return userRepository.findByType(type);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Seller addFollower(Long sellerId, String action) {
        Buyer buyer = this.getAuthenticatedBuyer();
        Seller seller = (Seller) this.findById(sellerId);
        if(action.equals("follow")){
            buyer.addFollowing(seller);
        }else{
            buyer.removeFollowing(seller);
        }
        userRepository.save(buyer);
        return seller;
    }

    @Override
    public boolean isFollowing(Long sellerId) {
        Buyer buyer = this.getAuthenticatedBuyer();
        Buyer result = userRepository.isFollowing(buyer.getId(),sellerId);
        if(result != null) return true;
        else return false;
    }

    @Override
    public Buyer getAuthenticatedBuyer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return (Buyer) findByEmail( auth.getName());
        } else {
            return null;
        }
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return findByEmail(auth.getName());
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getOrdersBySeller(Seller seller) {
        return userRepository.getOrdersBySeller(seller);
    }

    @Override
    public List<Order> getOrdersByBuyer() {
        Buyer buyer = this.getAuthenticatedBuyer();
        if(buyer!=null){
            return userRepository.getOrdersByBuyer(buyer);
        }else{
            throw new NullPointerException("Buyer not found!");
        }
    }

    @Override
    public List<Buyer> getFollewersNumber(Long sellerId) {
        return userRepository.getFollowersNumber(sellerId);
    }

    @Override
    public Boolean buyerHasCoupon() {
        Buyer buyer = this.getAuthenticatedBuyer();
        if(buyer.getCoupon() > 0) return true;
        else return false;
    }
}

