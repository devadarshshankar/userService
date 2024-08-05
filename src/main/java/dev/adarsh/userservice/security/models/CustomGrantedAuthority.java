package dev.adarsh.userservice.security.models;

import dev.adarsh.userservice.models.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@AllArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private Role role;

    @Override
    public String getAuthority() {
        return role.getName();
    }
}
