package kg.attractor.jobsearchjava27.dto;

import kg.attractor.jobsearchjava27.model.RespondedApplicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private Timestamp timestamp;
    private String content;
    private RespondedApplicant respondedApplicantId;
}
