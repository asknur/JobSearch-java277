package kg.attractor.jobsearchjava27.exception;

import java.nio.file.NoSuchFileException;

public class ResumeNotFoundException extends NoSuchFileException {
  public ResumeNotFoundException() {
    super("User not found: ");
  }
}
