package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class NewQuizzQuestionRequest {
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
