package kg.attractor.jobsearchjava27.model;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    private Long id;
    private LocalDateTime updateTime;
    private LocalDateTime createDate;
    private boolean isActive;
    private float salary;
    private Long categoryId;
    private String name;
    private Long applicantId;

}
