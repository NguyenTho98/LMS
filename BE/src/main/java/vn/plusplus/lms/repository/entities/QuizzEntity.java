package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "quizz")
@Getter @Setter
public class QuizzEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quizz_name")
    private String quizzName;

    @Column(name = "quizz_type")
    private String quizzType;

    @Column(name = "order_in_session")
    private Integer orderInSession;

    @Column(name = "session_id")
    private Integer sessionId;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;
}
