package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class NewSessionRequest {
    private String sessionName;
    private Integer orderInLesson;
    private String sessionType;
    private Integer lessionId;
    private String status;
}
