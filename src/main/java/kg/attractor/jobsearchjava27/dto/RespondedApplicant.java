package kg.attractor.jobsearchjava27.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private boolean confirmation;

    @NotNull
    @Min(1)
    private Vacancy vacancyId;

    @NotNull
    @Min(1)
    private Resume resumeId;
}
