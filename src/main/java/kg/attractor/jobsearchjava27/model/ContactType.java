package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactType {
    private int id;
    private String type;
    private int parentId;
}
