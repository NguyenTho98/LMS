package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class NewLessonRequest {
    private String lessonName;
    private String lessonDescription;
    private String lessonAvatar;
    private Integer orderInCourse;
    private Integer slideQuantity;
    private String lessonType;
    private Integer courseId;
    private String status;

}
