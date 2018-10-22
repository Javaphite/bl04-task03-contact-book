package ua.training.contactbook.model.exceptions;

public class NicknameNotUniqueException extends RuntimeException {

    public NicknameNotUniqueException(String message) {
        super(message);
    }
}
