package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class NewCourseRequest {
    private String courseName;
    private Integer lessonQuantity;
    private String courseDescription;
    private String courseAvatar;
    private String language;
    private String journey;
    private Integer orderInJourney;
    private String status;
}
