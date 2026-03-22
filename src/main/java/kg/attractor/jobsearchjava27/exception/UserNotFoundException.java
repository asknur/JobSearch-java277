package kg.attractor.jobsearchjava27.exception;

import java.nio.file.NoSuchFileException;

public class UserNotFoundException extends NoSuchFileException {
    public UserNotFoundException() {
        super("User not found: ");
    }
}
