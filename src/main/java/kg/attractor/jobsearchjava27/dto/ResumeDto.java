package kg.attractor.jobsearchjava27.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.attractor.jobsearchjava27.model.Category;
import kg.attractor.jobsearchjava27.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDto {
    private LocalDateTime updateTime;
    private LocalDateTime createDate;


    private boolean isActive;


    private float salary;

    @NotNull
    @Min(1)
    private Long categoryId;

    @NotBlank
    @Size(min = 1,  max = 20, message = "Should be name of resume")
    private String name;

    @NotNull
    @Min(1)
    private Long applicantId;

    @NotNull
    @Min(1)
    private Long id;

}
