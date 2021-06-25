package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UpdateUserExamAnswerRequest {
    private Integer userId;
    private Integer examQuestion;
    private String answer;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String status;
}
