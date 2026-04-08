package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Data
@Builder
@Getter
@Setter
public class Vacancy {
    private Long id;
    private Timestamp updateTime;
    private Timestamp createdTime;
    private Long authorId;
    private boolean isActive;
    private Integer expTo;
    private Integer expFrom;
    private float salary;
    private Long categoryId;
    private String description;
    private String name;

}
