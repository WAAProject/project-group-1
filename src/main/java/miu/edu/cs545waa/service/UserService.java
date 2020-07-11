package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.User;

import java.util.List;

public interface UserService {

    public List<User> getAll();
    public User save(User user);

}
