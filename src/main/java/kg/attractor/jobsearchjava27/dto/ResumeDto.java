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
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDto {
    private Long id;

    @NotBlank(message = "Name cant be empty")
    private String name;

    @NotNull(message = "Enter salary")
    @Min(value = 0, message = "Salary cant be negative")
    private Float salary;

    @NotNull(message = "Enter category")
    private Long categoryId;

    private Long applicantId;

    private Boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updateTime;

    private List<WorkExperienceInfoDto> workExperiences = new ArrayList<>();
    private List<EducationInfoDto> educations = new ArrayList<>();
    private List<ContactInfoDto> contacts = new ArrayList<>();

}
