package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAssignmentRequest {
    private String assignmentTitle;
    private String assignmentDescription;
    private Integer orderInLesson;
    private String assignmentType;
    private Integer lessonId;
    private String status;
}
