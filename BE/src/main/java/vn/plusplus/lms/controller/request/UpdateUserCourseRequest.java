package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserCourseRequest {
    private Integer courseId;
    private Integer userId;
}
