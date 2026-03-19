package kg.attractor.jobsearchjava27.dto;

import kg.attractor.jobsearchjava27.model.ContactType;
import kg.attractor.jobsearchjava27.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfoDto {
    private String value;
    private Resume resumeId;
    private ContactType typeId;
}
