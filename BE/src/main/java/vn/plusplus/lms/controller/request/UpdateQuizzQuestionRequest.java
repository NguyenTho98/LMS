package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuizzQuestionRequest {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String type;
    private Integer orderInQuizz;
    private Integer quizzId;
    private String status;
}
