package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.QuizzEntity;
import vn.plusplus.lms.repository.entities.QuizzQuestionEntity;

import java.util.List;

@Repository
public interface QuizzQuestionRepository extends JpaRepository<QuizzQuestionEntity, Integer> {
    QuizzQuestionEntity findOneById(Integer quizzQuestionId);

    QuizzQuestionEntity findOneByIdAndStatus(Integer quizzQuestionId, String status);

    List<QuizzQuestionEntity> findAllByQuizzIdAndStatus(Integer quizzId,String status);

    @Query(nativeQuery = true,value = "SELECT course.id FROM course \n" +
            "JOIN lesson ON course.id = lesson.course_id \n" +
            "JOIN session ON lesson.id = session.lesson_id \n" +
            "JOIN quizz ON session.id = quizz.session_id \n" +
            "JOIN quizz_question ON quizz.id = quizz_question.quizz_id\n" +
            "WHERE quizz_question.id =:quizzQuestionId \n" +
            "AND quizz_question.status='ACTIVE'\n" +
            "AND quizz.status='ACTIVE' \n" +
            "AND session.status='ACTIVE' \n" +
            "AND lesson.status='ACTIVE'\n" +
            "AND course.status='ACTIVE';")
    Integer findCourseIdByQuizzQuestionId(@Param("quizzQuestionId") Integer quizzQuestionId);

    @Query(nativeQuery = true,value = "SELECT * FROM quizz_question WHERE quizz_question.status='ACTIVE' AND "
        + "quizz_question.quizz_id=:quizzId\n")
    List<QuizzQuestionEntity> findQuizzQuestionByQuizzId(Integer quizzId);
}
