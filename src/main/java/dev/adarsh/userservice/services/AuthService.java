package dev.adarsh.userservice.services;

public interface AuthService {
    public String login(String email,String password);
    public String validate(String token);
}
