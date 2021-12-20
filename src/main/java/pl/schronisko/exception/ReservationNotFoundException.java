package pl.schronisko.exception;

public class ReservationNotFoundException extends Throwable{
    public ReservationNotFoundException(String s) {
        super(s);
    }
}
