package vn.plusplus.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer id;
    private String courseName;
    private Integer lessonQuantity;
    private String courseDescription;
    private String courseAvatar;
    private String language;
    private String journey;
    private Integer orderInJourney;

}
