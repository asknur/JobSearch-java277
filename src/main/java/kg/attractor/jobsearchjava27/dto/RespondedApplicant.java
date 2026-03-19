package kg.attractor.jobsearchjava27.dto;

import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.model.Vacancy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespondedApplicant {
    private boolean confirmation;
    private Vacancy vacancyId;
    private Resume resumeId;
}
