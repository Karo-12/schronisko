package pl.schronisko.exception;

public class AnimalNotFoundException extends Throwable{
    public AnimalNotFoundException(String s) {
        super(s);
    }
}
