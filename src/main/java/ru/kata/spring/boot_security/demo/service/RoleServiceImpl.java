package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> getAllAvailableRoles() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        List<Role> newRolesArray = new ArrayList<>();
        newRolesArray.add(adminRole);
        newRolesArray.add(userRole);
        return newRolesArray;
    }
}
