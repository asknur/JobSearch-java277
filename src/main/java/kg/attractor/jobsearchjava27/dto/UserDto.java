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
    @Size(min = 5, message = "Should be phone number")
    private String phoneNumber;

    @Size(min = 1, message = "Should be text")
    private String text;

    @NotBlank
    @Email
    private String email;

    @Min(1)
    private Integer age;

    @NotBlank
    @Size(min = 4, max = 50, message = "Should be name")
    private String name;

    @Size(min = 4, max = 50, message = "Should be surname")
    private String surname;

    @NotBlank
    @Size(min = 2, max = 10, message = "Lenght must be >= 2 and <= 10")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one uppercase letter, one number")
    private String password;

    private Long id;
}
