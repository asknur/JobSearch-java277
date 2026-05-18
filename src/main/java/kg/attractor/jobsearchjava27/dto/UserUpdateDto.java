package kg.attractor.jobsearchjava27.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {

    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "{validation.name.notBlank}")
    @Size(min = 2, max = 50)
    private String surname;

    @NotNull(message = "{validation.name.notBlank}")
    @Min(value = 14, message = "{validation.age.min}")
    @Max(value = 100)
    private Integer age;

    private String phoneNumber;
}
