package kg.attractor.jobsearchjava27.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private Integer id;
    private String name;
    private int parentId;

}
