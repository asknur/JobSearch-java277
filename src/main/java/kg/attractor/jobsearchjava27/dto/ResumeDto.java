package kg.attractor.jobsearchjava27.dto;

import kg.attractor.jobsearchjava27.model.Category;
import kg.attractor.jobsearchjava27.model.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResumeDto {
    private Timestamp updateTime;
    private Timestamp createDate;
    private boolean isActive;
    private float salary;
    private Category categoryId;
    private String name;
    private User applicantId;
}
