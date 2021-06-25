package vn.plusplus.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
    private Integer id;
    private String sessionName;
    private Integer orderInLesson;
    private String sessionType;
    private Integer lessonId;
}
