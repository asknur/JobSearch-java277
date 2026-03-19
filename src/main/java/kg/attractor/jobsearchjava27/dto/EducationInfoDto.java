package kg.attractor.jobsearchjava27.dto;

import kg.attractor.jobsearchjava27.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationInfoDto {
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;
    private String program;
    private String institution;
    private Resume resumeId;
}
