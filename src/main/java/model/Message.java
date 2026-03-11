package model;

import java.sql.Timestamp;

public class Message {
    private int id;
    private Timestamp timestamp;
    private String content;
    private RespondedApplicant respondedApplicantId;
}
