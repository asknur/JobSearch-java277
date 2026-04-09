package kg.attractor.jobsearchjava27.model;

import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
    private Long id;
    private LocalDateTime updateTime;
    private LocalDateTime createdTime;
    private Long authorId;
    private boolean isActive;
    private Integer expTo;
    private Integer expFrom;
    private float salary;
    private Long categoryId;
    private String description;
    private String name;

}
