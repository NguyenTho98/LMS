package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "quizz_question")
@Getter
@Setter
public class QuizzQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "option_a")
    private String optionA;

    @Column(name = "option_b")
    private String optionB;

    @Column(name = "option_c")
    private String optionC;

    @Column(name = "option_d")
    private String optionD;

    @Column(name = "answer")
    private String answer;

    @Column(name = "type")
    private String type;

    @Column(name = "order_in_quizz")
    private Integer orderInQuizz;

    @Column(name = "quizz_id")
    private Integer quizzId;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;

}