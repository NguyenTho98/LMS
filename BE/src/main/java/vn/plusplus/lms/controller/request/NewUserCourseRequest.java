package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class NewUserCourseRequest {
    private Integer courseId;
    private Integer userId;
}
