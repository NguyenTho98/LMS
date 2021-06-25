package vn.plusplus.lms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.LessonEntity;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity,Integer> {
    LessonEntity findOneById(Integer id);
    LessonEntity findOneByIdAndStatus(Integer id,String status);

    @Query(nativeQuery = true,value = "SELECT course.id FROM course \n" +
            "JOIN lesson ON course.id = lesson.course_id \n" +
            "WHERE lesson.id =:lessonId\n" +
            "AND lesson.status='ACTIVE'\n" +
            "AND course.status='ACTIVE';")
    Integer findCourseIdByLessonId(@Param("lessonId") Integer lessonId);

    @Query(nativeQuery = true,value = "SELECT * FROM lesson WHERE lesson.status='ACTIVE' AND "
        + "lesson.course_id=:courseId\n")
    List<LessonEntity> findLessonByCourseId(Integer courseId);
}
