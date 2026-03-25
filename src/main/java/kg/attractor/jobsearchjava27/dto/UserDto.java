package kg.attractor.jobsearchjava27.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String accountType;
    private String avatar;
    private String phoneNumber;
    private String text;

    @NotBlank
    @Email
    private String email;
    private Integer age;

    @NotBlank
    private String name;
    private String surname;

    @NotBlank
    @Size(min = 4, max = 10, message = "Lenght must be >= 4 and <= 10")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one uppercase letter, one number")
    private String password;
}
