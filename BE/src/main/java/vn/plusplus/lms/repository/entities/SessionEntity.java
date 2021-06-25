package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "session")
@Getter @Setter
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "order_in_lesson")
    private Integer orderInLesson;

    @Column(name = "session_type")
    private String sessionType;

    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;
}
