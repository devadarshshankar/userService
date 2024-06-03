package dev.adarsh.userservice.services;

import dev.adarsh.userservice.models.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    public Role createRole(String name) {
        return new Role(name);
    }
}
