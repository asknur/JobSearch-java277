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

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyDto {
    private int respondedCount;

    private LocalDateTime updateTime;

    private LocalDateTime createdDate;

    @NotNull
    @Min(1)
    private Long authorId;

    @NotNull
    private boolean isActive;

    @NotNull
    private Integer expTo;

    @NotNull
    private Integer expFrom;

    @NotNull
    private float salary;

    @NotNull
    private Long categoryId;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Min(1)
    private Long id;
}
