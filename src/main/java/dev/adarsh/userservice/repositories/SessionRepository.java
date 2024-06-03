package dev.adarsh.userservice.repositories;

import dev.adarsh.userservice.models.Session;
import dev.adarsh.userservice.models.Token;
import dev.adarsh.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, User> {


    Optional<Session> findByTokenAndUser_Id(String token, Long userId);
}
