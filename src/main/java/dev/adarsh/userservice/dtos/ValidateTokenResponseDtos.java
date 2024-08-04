package dev.adarsh.userservice.dtos;

import dev.adarsh.userservice.models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDtos {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
