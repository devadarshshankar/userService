package dev.adarsh.userservice.dtos;

import dev.adarsh.userservice.models.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String email;
    private String hashedPassword;
    private String fullName;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;
}
