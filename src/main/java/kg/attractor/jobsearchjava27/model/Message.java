package kg.attractor.jobsearchjava27.model;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Builder
public class Message {
    private int id;
    private Timestamp timestamp;
    private String content;
    private RespondedApplicant respondedApplicantId;
}
