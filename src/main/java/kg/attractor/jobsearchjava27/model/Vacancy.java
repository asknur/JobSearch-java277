package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;

import java.security.Timestamp;

@Data
@Builder
public class Vacancy {
    private int id;
    private Timestamp updateTime;
    private Timestamp createdTime;
    private User authorId;
    private boolean isActive;
    private int expTo;
    private int expFrom;
    private float salary;
    private Category categoryId;
    private String description;
    private String name;

}
