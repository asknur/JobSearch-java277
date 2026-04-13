package kg.attractor.jobsearchjava27.exception;

import java.nio.file.NoSuchFileException;

public class UserNotFoundException extends NotFoundEntryException {
    public UserNotFoundException() {
        super("User not found: ");
    }
}
