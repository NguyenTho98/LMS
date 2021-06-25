package vn.plusplus.lms.repository;

import io.swagger.models.auth.In;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.SessionEntity;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity,Integer> {
    SessionEntity findOneById(Integer sessionId);

    SessionEntity findOneByIdAndStatus(Integer sessionId,String status);

    List<SessionEntity> findAllByLessonIdAndStatus(Integer lessonId,String status);

    @Query(nativeQuery = true,value = "SELECT course.id FROM course \n" +
            "JOIN lesson ON course.id = lesson.course_id \n" +
            "JOIN session ON lesson.id = session.lesson_id \n" +
            "WHERE session.id =:sessionId \n" +
            "AND session.status='ACTIVE' \n" +
            "AND lesson.status='ACTIVE'\n" +
            "AND course.status='ACTIVE';")
    Integer findCourseIdBySessionId(@Param("sessionId") Integer sessionId);

    @Query(nativeQuery = true,value = "SELECT * FROM session WHERE session.status='ACTIVE' AND "
        + "session.lesson_id=:lessonId\n")
    List<SessionEntity> findSessionByLessonId(Integer lessonId);
}
