package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewExamRequest {
    private String examName;
    private String examType;
    private Integer orderInCourse;
    private Integer courseId;
    private String status;
}
