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
    private Long id;

    @NotBlank(message = "Name cant be empty")
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank(message = "Description cant be empty")
    @Size(min = 1, max = 255)
    private String description;

    @NotNull(message = "Enter salary")
    @Min(value = 0, message = "Salary cant be negative")
    private Float salary;

    @NotNull(message = "Enter category")
    private Long categoryId;

    private Long authorId;

    @Min(value = 0)
    private Integer expFrom;

    @Min(value = 0)
    private Integer expTo;

    private Boolean isActive;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private int respondedCount;
}
