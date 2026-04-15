package kg.attractor.jobsearchjava27.model;

import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacancies", schema = "public")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User authorId;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "exp_to")
    private Integer expTo;

    @Column(name = "exp_from")
    private Integer expFrom;

    @Column(name = "salary")
    private float salary;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

}
