package ru.kata.spring.boot_security.demo.service;


import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setName(user.getName());
        user.setSurname(user.getSurname());
        user.setAddress(user.getAddress());
        user.setRoles(user.getRoles());
        user.setFn(user.getFn());
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        userRepository.saveAndFlush(user);
    }

    @Transactional
    @Override
    public User getUser(int id) {
        return userRepository.getById(id);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setName(user.getName());
        user.setSurname(user.getSurname());
        user.setAddress(user.getAddress());
        user.setRoles(user.getRoles());
        user.setFn(user.getFn());
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    @Override
    public User getByUsername(String email) {
        return userRepository.getUserByUsername(email);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.delete(userRepository.getById(id));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with email" + username + " not found"));
        }
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
