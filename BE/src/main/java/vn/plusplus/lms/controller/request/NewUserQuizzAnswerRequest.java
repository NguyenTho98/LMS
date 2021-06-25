package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class NewUserQuizzAnswerRequest {
    private Integer userId;
    private Integer quizzQuestion;
    private String answer;
    private String status;

}
