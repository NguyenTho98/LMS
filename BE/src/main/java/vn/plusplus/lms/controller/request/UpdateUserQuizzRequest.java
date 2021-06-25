package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserQuizzRequest {
    private Integer userId;
    private Integer quizzId;
    private String status;
    private Double score;
    private String comment;
}
