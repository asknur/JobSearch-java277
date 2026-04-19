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

    @NotBlank(message = "Name cant be empty")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Surname cant be empty")
    @Size(min = 2, max = 50)
    private String surname;

    @NotNull(message = "Age cant be empty")
    @Min(value = 14, message = "Min age 14")
    @Max(value = 100)
    private Integer age;

    private String phoneNumber;
}
