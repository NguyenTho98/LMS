package vn.plusplus.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LessonDTO {
    private Integer id;
    private String lessonName;
    private String lessonDescription;
    private String lessonAvatar;
    private Integer orderInCourse;
    private String lessonType;
    private Integer courseId;
}
