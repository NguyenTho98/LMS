package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.AssignmentEntity;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity,Integer> {
    AssignmentEntity findOneById(Integer assignmentId);

    AssignmentEntity findOneByIdAndStatus(Integer assignmentId,String status);

    List<AssignmentEntity> findAllByLessonIdAndStatus(Integer lessonId,String status);

    @Query(nativeQuery = true,value = "SELECT course.id FROM course \n" +
            "JOIN lesson ON course.id = lesson.course_id \n" +
            "JOIN assignment ON lesson.id = assignment.lesson_id\n" +
            "WHERE assignment.id =:assignmentId \n" +
            "AND assignment.status='ACTIVE'\n" +
            "AND lesson.status='ACTIVE'\n" +
            "AND course.status='ACTIVE';")
    Integer findCourseIdByAssignmentId(@Param("assignmentId") Integer assignmentId);

    @Query(nativeQuery = true,value = "SELECT * FROM assignment WHERE assignment.status='ACTIVE' AND "
        + "assignment.lesson_id=:lessonId\n")
    List<AssignmentEntity> findAssignmentByLessonId(Integer lessonId);
}
