package dev.adarsh.userservice.dtos;

import dev.adarsh.userservice.models.Role;
import dev.adarsh.userservice.models.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    @ManyToMany
    private Set<Role> roles= new HashSet<>();

    public static UserDto from(User user){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
