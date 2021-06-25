package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.MaterialEntity;
import vn.plusplus.lms.repository.entities.QuizzEntity;

import java.util.List;

@Repository
public interface QuizzRepository extends JpaRepository<QuizzEntity, Integer> {
    QuizzEntity findOneById(Integer quizzId);

    QuizzEntity findOneByIdAndStatus(Integer quizzId, String status);

    List<QuizzEntity> findAllBySessionIdAndStatus(Integer sessionId, String status);

    @Query(nativeQuery = true,value = "SELECT course.id FROM course \n" +
            "JOIN lesson ON course.id = lesson.course_id \n" +
            "JOIN session ON lesson.id = session.lesson_id \n" +
            "JOIN quizz ON session.id = quizz.session_id \n" +
            "WHERE quizz.id =:quizzId \n" +
            "AND quizz.status='ACTIVE' \n" +
            "AND session.status='ACTIVE' \n" +
            "AND lesson.status='ACTIVE'\n" +
            "AND course.status='ACTIVE';")
    Integer findCourseIdByQuizzId(@Param("quizzId") Integer quizzId);

    @Query(nativeQuery = true,value = "SELECT * FROM quizz WHERE quizz.status='ACTIVE' AND "
        + "quizz.session_id=:sessionId\n")
    List<QuizzEntity> findQuizzBySessionId(Integer sessionId);
}
