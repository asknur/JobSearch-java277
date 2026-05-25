package kg.attractor.jobsearchjava27.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.attractor.jobsearchjava27.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationInfoDto {
    @NotBlank(message = "{validation.education.institution}")
    @Size(min = 1, max = 100)
    private String institution;

    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 1, max = 100)
    private String program;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 1, max = 100)
    private String degree;
}
