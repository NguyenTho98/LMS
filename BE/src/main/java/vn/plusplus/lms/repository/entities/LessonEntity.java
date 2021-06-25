package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lesson")
@Getter @Setter
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "lesson_description")
    private String lessonDescription;

    @Column(name = "lesson_avatar")
    private String lessonAvatar;

    @Column(name = "order_in_course")
    private Integer orderInCourse;

    @Column(name = "lesson_type")
    private String lessonType;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;

}
