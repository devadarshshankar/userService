package dev.adarsh.userservice.dtos;

import dev.adarsh.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private String token;
    private Long userId;
}
