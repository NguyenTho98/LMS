package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.ExamEntity;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
    ExamEntity findOneById(Integer examId);

    ExamEntity findOneByIdAndStatus(Integer examId, String status);

    @Query(nativeQuery = true,value = "SELECT course.id FROM course \n" +
            "JOIN exam ON course.id = exam.course_id \n" +
            "WHERE exam.id =:examId \n" +
            "AND exam.status='ACTIVE'\n" +
            "AND course.status='ACTIVE';")
    Integer findCourseIdByExamId(@Param("examId") Integer examId);
}
