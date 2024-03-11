package dev.adarsh.userservice.controllers;

import dev.adarsh.userservice.services.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable ("email" )String email,@PathVariable("password") String password){
       return email+" "+password;
    }

    @GetMapping("/validate/{token}")
    public boolean validate(@PathVariable("token") String token){
        return true;
    }
}
