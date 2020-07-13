package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;

import java.util.List;

public interface UserService {

    public User findByEmail(String email);
    public List<User> getAll();
    public List<User> findByType(String type);
    public User save(User user);

    public Buyer getAuthenticatedBuyer();
    public User getAuthenticatedUser();

}
