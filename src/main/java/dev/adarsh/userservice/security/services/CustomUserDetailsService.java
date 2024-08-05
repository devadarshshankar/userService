package dev.adarsh.userservice.security.services;

import dev.adarsh.userservice.models.User;
import dev.adarsh.userservice.repositories.UserRepository;
import dev.adarsh.userservice.security.models.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException(username+" doesn't exist");
        }

        return new CustomUserDetails(user.get());
    }
}
