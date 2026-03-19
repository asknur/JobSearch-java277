package kg.attractor.jobsearchjava27.dto;

import kg.attractor.jobsearchjava27.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkExperienceInfoDto {
    private String responsibilities;
    private String position;
    private String companyName;
    private Integer years;
    private Resume resumeId;
}
