package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "exam")
@Getter @Setter
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "exam_type")
    private String examType;

    @Column(name = "order_in_course")
    private Integer orderInCourse;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;
}
