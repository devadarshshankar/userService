package dev.adarsh.userservice.exceptions;

public class PasswordNotMatchesException extends Exception{
    public PasswordNotMatchesException(String message){
        super(message);
    }
}
