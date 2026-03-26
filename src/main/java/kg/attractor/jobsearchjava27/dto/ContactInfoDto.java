package kg.attractor.jobsearchjava27.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.attractor.jobsearchjava27.model.ContactType;
import kg.attractor.jobsearchjava27.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfoDto {
    @NotBlank
    @Size(min = 1, max = 20, message = "Should be value")
    private String value;

    @NotNull
    @Min(1)
    private Resume resumeId;

    @NotNull
    @Min(1)
    private ContactType typeId;
}
