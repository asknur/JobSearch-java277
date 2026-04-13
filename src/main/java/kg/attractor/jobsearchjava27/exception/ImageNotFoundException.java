package kg.attractor.jobsearchjava27.exception;

import java.nio.file.NoSuchFileException;

public class ImageNotFoundException extends NotFoundEntryException {
    public ImageNotFoundException() {
        super("Image not found");
    }
}
