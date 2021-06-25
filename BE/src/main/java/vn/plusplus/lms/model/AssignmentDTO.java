package vn.plusplus.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Integer id;
    private String assignmentTitle;
    private String assignmentDescription;
    private Integer orderInLesson;
    private String assignmentType;
    private Integer lessonId;
}
