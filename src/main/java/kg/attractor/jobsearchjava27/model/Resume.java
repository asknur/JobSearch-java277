package kg.attractor.jobsearchjava27.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resumes", schema = "public")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "created_date")
    private LocalDateTime createDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "salary")
    private float salary;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @OneToMany(mappedBy = "resume")
    private List<WorkExperienceInfo> workExperienceInfo;

    @OneToMany(mappedBy = "resume")
    private List<EducationInfo> educations;

    @OneToMany(mappedBy = "resume")
    private List<ContactInfo> contacts;


}
