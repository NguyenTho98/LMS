package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "assignment")
@Getter @Setter
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "assignment_title")
    private String assignmentTitle;

    @Column(name = "assignment_description")
    private String assignmentDescription;

    @Column(name = "order_in_lesson")
    private Integer orderInLesson;

    @Column(name = "assignment_type")
    private String assignmentType;

    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;
}
