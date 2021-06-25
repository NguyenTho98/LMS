package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course")
@Getter @Setter
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "lesson_quantity")
    private Integer lessonQuantity;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "course_avatar")
    private String courseAvatar;

    @Column(name = "language")
    private String language;

    @Column(name = "journey")
    private String journey;

    @Column(name = "order_in_journey")
    private Integer orderInJourney;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;
}
