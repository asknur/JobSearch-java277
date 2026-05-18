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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkExperienceInfoDto {
    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 1, max = 100)
    private String responsibilities;

    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 1, max = 100)
    private String position;

    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 1, max = 100)
    private String companyName;

    @NotNull(message = "{validation.name.notBlank}")
    @Min(1)
    private Integer years;

    @NotNull(message = "{validation.name.notBlank}")
    @Min(1)
    private Resume resumeId;
}
