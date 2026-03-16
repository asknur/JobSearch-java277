package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactInfo {
    private int id;
    private String value;
    private Resume resumeId;
    private ContactType typeId;
}
