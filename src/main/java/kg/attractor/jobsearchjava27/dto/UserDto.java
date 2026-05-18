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
public class UserDto {
    @NotBlank
    private String accountType;

    private String avatar;

    @NotBlank
    @Size(min = 5, message = "{validation.name.notBlank}")
    private String phoneNumber;

    @Size(min = 1, message = "{validation.name.notBlank}")
    private String text;

    @NotBlank(message = "{validation.email.notBlank}")
    @Email
    private String email;

    @Min(1)
    private Integer age;

    @NotBlank
    @Size(min = 4, max = 50, message = "{validation.name.notBlank}")
    private String name;

    @Size(min = 4, max = 50, message = "{validation.name.notBlank}")
    private String surname;

    @NotBlank
    @Size(min = 2, max = 10, message = "Lenght must be >= 2 and <= 10")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "{validation.password.size}")
    private String password;

    private Long id;
}
