package kg.attractor.jobsearchjava27.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_experience_info", schema = "public")
public class WorkExperienceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Column(name = "position")
    private String position;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "years")
    private Integer years;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
