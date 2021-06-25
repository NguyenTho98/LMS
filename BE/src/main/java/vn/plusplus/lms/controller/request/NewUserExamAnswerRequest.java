package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;


@Getter
@Setter
public class NewUserExamAnswerRequest {
    private Integer userId;
    private Integer examQuestion;
    private String answer;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String status;
}
