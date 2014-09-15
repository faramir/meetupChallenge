package pl.jug.torun.meetup.api;

public class MeetupException extends RuntimeException {

    public MeetupException(String message, Throwable cause) {
        super(message, cause);
    }
}
