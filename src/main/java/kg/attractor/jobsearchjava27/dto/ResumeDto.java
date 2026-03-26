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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDto {
    private Timestamp updateTime;
    private Timestamp createDate;

    @NotNull
    private boolean isActive;

    @NotNull
    private float salary;

    @NotNull
    @Min(1)
    private Category categoryId;

    @NotBlank
    @Size(min = 1,  max = 20, message = "Should be name of resume")
    private String name;

    @NotNull
    @Min(1)
    private User applicantId;
}
