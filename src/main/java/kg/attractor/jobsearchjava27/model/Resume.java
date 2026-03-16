package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Resume {
    private int id;
    private Timestamp updateTime;
    private Timestamp createDate;
    private boolean isActive;
    private float salary;
    private Category categoryId;
    private String name;
    private User applicantId;

}
