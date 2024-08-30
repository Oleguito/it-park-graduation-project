package ru.itpark.projectservice.infrastructure.exceptions.exceptions;

public class UserWasAlreadyInvitedException extends RuntimeException {
    public UserWasAlreadyInvitedException() {
    }
    
    public UserWasAlreadyInvitedException(String message) {
        super(message);
    }
    
    public UserWasAlreadyInvitedException(String message, Throwable cause) {
        super(message, cause);
    }
}
