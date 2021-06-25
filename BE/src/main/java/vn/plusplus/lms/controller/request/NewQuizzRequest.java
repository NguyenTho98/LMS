package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewQuizzRequest {
    private String quizzName;
    private String quizzType;
    private Integer orderInSession;
    private Integer sessionId;
    private String status;
}
