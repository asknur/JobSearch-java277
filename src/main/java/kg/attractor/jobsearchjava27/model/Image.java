package kg.attractor.jobsearchjava27.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private Long id;
    private Long userId;
    private String fileName;

}
