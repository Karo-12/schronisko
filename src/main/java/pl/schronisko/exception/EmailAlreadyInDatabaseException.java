package pl.schronisko.exception;

public class EmailAlreadyInDatabaseException extends Throwable{

    public EmailAlreadyInDatabaseException(String s) {
        super(s);
    }
}
