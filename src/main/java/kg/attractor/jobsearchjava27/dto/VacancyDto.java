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

    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 1, max = 255)
    private String description;

    @NotNull(message = "{validation.name.notBlank}")
    @Min(value = 0, message = "{validation.salary.min}")
    private Float salary;

    @NotNull(message = "{validation.categoryId.notNull}")
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
