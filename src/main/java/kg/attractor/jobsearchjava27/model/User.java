package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {
    private int id;
    private String accountType;
    private String avatar;
    private String phoneNumber;
    private String text;
    private String email;
    private int age;
    private String name;
    private String surname;
    private String password;

}
