package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserExamRequest {
    private Integer userId;
    private Integer examId;
    private String status;
    private Double score;
    private String comment;
}
