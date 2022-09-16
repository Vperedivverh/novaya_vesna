package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;

    public Init(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void initUsers() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        Set<Role> usersRoles1 = new HashSet<>();
        Set<Role> usersRoles2 = new HashSet<>();

        usersRoles1.add(adminRole);
        usersRoles2.add(userRole);

        User user1 = new User("Василий", "Шукшин", "Рязань", 4545145, "111", "111", usersRoles1);
        User user2 = new User("Роберт", "Желязны", "Карачи", 777888999, "222", "222", usersRoles2);

        userService.saveUser(user1);
        userService.saveUser(user2);
    }

}