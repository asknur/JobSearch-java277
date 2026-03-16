package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespondedApplicant {
    private int id;
    private boolean confirmation;
    private Vacancy vacancyId;
    private Resume resumeId;

}
