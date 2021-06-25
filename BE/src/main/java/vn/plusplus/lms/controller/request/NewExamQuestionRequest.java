package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewExamQuestionRequest {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String type;
    private Integer orderInExam;
    private Integer examId;
    private String status;
}
