package dev.adarsh.userservice.controllers;

import dev.adarsh.userservice.dtos.*;
import dev.adarsh.userservice.exceptions.UserAlreadyExistException;
import dev.adarsh.userservice.models.SessionStatus;
import dev.adarsh.userservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto requestDto) throws Exception{
        return authService.login(requestDto.getEmail(),requestDto.getPassword());
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request){
        return authService.logout(request.getToken(), request.getUserId());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto requestDto) throws UserAlreadyExistException {
        UserDto userDto=authService.signUp(requestDto.getEmail(),requestDto.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto requestDto){

        SessionStatus sessionStatus=authService.validate(requestDto.getToken(),requestDto.getUserId());
        return new ResponseEntity<>(sessionStatus,HttpStatus.OK) ;
    }
}
