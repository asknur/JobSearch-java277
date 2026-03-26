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
    @NotBlank
    @Size(min = 1, max = 100, message = "Should be degree")
    private String degree;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotBlank
    @Size(min = 1, max = 100, message = "Should be program")
    private String program;

    @NotBlank
    @Size(min = 1, max = 100, message = "Should be institution")
    private String institution;

    @NotNull
    @Min(1)
    private Resume resumeId;
}
