package dev.adarsh.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.adarsh.userservice.models.Role;
import dev.adarsh.userservice.models.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@JsonDeserialize
public class CustomUserDetails implements UserDetails {
   // private User user;
    private List<GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public CustomUserDetails(User user){
        authorities=new ArrayList<>();

        for(Role role: user.getRoles()){
            authorities.add(new CustomGrantedAuthority(role));
        }

        this.username=user.getEmail();
        this.password=user.getPassword();
        this.accountNonExpired =true;
        this.accountNonLocked =true;
        this.credentialsNonExpired =true;
        this.enabled =true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<CustomGrantedAuthority> customGrantedAuthorities=new ArrayList<>();
//
//        for(Role role: user.getRoles()){
//            customGrantedAuthorities.add(new CustomGrantedAuthority(role));
//        }
//        return customGrantedAuthorities;
        return authorities;
    }

    @Override
    public String getPassword() {
//        return user.getPassword();
        return this.password;
    }


    @Override
    public String getUsername() {
//        return user.getEmail();
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
//        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
//        return true;//if (Date.now-lastPasswordUpdateDate>90){return false} return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
//        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
//        return true;
    }
}
