package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserQuizzAnswerRequest {
    private Integer userId;
    private Integer quizzQuestion;
    private String answer;
    private String status;
}
