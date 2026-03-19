package kg.attractor.jobsearchjava27.dto;

import kg.attractor.jobsearchjava27.model.Category;
import kg.attractor.jobsearchjava27.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyDto {
    private Timestamp updateTime;
    private Timestamp createdTime;
    private User authorId;
    private boolean isActive;
    private Integer expTo;
    private Integer expFrom;
    private float salary;
    private Category categoryId;
    private String description;
    private String name;
}
