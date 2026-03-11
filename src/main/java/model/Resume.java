package model;

import java.sql.Timestamp;

public class Resume {
    private int id;
    private Timestamp updateTime;
    private Timestamp createDate;
    private boolean isActive;
    private float salary;
    private Category categoryId;
    private String name;
    private User applicantId;

}
