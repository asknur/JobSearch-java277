package kg.attractor.jobsearchjava27.exception;

import java.nio.file.NoSuchFileException;

public class ResumeNotFoundException extends NotFoundEntryException {
  public ResumeNotFoundException() {
    super("Resume not found: ");
  }
}
