package dev.adarsh.userservice.services;

import dev.adarsh.userservice.dtos.UserDto;
import dev.adarsh.userservice.exceptions.PasswordNotMatchesException;
import dev.adarsh.userservice.exceptions.UserAlreadyExistException;
import dev.adarsh.userservice.exceptions.UserDoesNotExistException;
import dev.adarsh.userservice.models.Session;
import dev.adarsh.userservice.models.SessionStatus;
import dev.adarsh.userservice.models.User;
import dev.adarsh.userservice.repositories.SessionRepository;
import dev.adarsh.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService  {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository,SessionRepository sessionRepository){//,BCryptPasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.sessionRepository=sessionRepository;
        this.bCryptPasswordEncoder=new BCryptPasswordEncoder();
    }

    public ResponseEntity<UserDto> login(String email, String password) throws Exception {
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserDoesNotExistException("User with "+email+" does not exist.");
        }

        User user=userOptional.get();

        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
//            throw new PasswordNotMatchesException("Wrong Password");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String token= RandomStringUtils.randomAscii(20);
        MultiValueMapAdapter<String,String> headers=new MultiValueMapAdapter<>(new HashMap<>());
        headers.add("AUTH_TOKEN",token);

        Session  session=new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        UserDto userDto=UserDto.from(user);
        ResponseEntity<UserDto> response=new ResponseEntity<>(
                userDto,
                headers,
                HttpStatus.OK
        );

        return response;
    }

    public SessionStatus validate(String token,Long userId) {
        Optional<Session> sessionOptional=sessionRepository.findByTokenAndUser_Id(token,userId);

        if(sessionOptional.isEmpty()){
            return SessionStatus.INVALID;
        }

        Session session=sessionOptional.get();
        if(!session.getSessionStatus().equals(SessionStatus.ACTIVE)){
            return SessionStatus.EXPIRED;
        }
//        if(session.getExpiringAt()>new Date()){
//            return SessionStatus.EXPIRED;
//        }

        return SessionStatus.ACTIVE;
    }


    public UserDto signUp(String email, String password) throws UserAlreadyExistException {
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(!userOptional.isEmpty()){
            throw new UserAlreadyExistException("User with "+email+" already exists");
        }

        User user=new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser= userRepository.save(user);

        return UserDto.from(savedUser);
    }

    public ResponseEntity<Void> logout(String token, Long userId){
        Optional<Session> sessionOptional=sessionRepository.findByTokenAndUser_Id(token,userId);
        if(sessionOptional.isEmpty()){
            return  null;
        }

        Session session=sessionOptional.get();
        session.setSessionStatus(SessionStatus.LOGGED_OUT);
        sessionRepository.save(session);

        return ResponseEntity.ok().build();

    }
}
