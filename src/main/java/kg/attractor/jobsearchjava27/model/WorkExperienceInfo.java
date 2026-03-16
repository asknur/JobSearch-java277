package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkExperienceInfo {
    private int id;
    private String responsibilities;
    private String position;
    private String companyName;
    private int years;
    private Resume resumeId;

}
