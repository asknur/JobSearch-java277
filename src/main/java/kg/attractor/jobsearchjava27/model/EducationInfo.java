package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class EducationInfo {
    private Integer id;
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;
    private String program;
    private String institution;
    private Resume resumeId;

}
