package pl.schronisko.exception;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String s) {
        super(s);
    }
}
