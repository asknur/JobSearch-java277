package kg.attractor.jobsearchjava27.dto;

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
    private String email;
    private Integer age;
    private String name;
    private String surname;
    private String password;
}
