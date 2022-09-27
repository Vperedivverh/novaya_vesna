package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {


    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUserById(int id);

    public User updateUser(User user);

    public User findByUsername(String username);

    public void deleteUserById(int id);

    @Override
    public UserDetails loadUserByUsername(String username);
}
