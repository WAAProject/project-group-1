package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import miu.edu.cs545waa.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
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

//    @Override
//    public List<Seller> getAllSellers() {
//        return userRepository.findAll();
//    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findByType(String type) {
        return userRepository.findByType(type);
    }
}
