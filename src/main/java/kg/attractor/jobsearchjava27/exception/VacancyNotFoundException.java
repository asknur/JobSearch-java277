package kg.attractor.jobsearchjava27.exception;

import java.nio.file.NoSuchFileException;

public class VacancyNotFoundException extends NoSuchFileException {
    public VacancyNotFoundException() {
        super("Vacancy Not Found");
    }
}
