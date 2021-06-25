package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.ExamQuestionEntity;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestionEntity, Integer> {
    ExamQuestionEntity findOneById(Integer examQuestionId);

    ExamQuestionEntity findOneByIdAndStatus(Integer examQuestionId, String status);

    @Query(nativeQuery = true,value = "SELECT course.id FROM course \n" +
            "JOIN exam ON course.id = exam.course_id \n" +
            "JOIN exam_question ON exam.id = exam_question.exam_id\n" +
            "WHERE exam_question.id =:examQuestionId \n" +
            "AND exam_question.status='ACTIVE'\n" +
            "AND exam.status='ACTIVE'\n" +
            "AND course.status='ACTIVE';")
    Integer findCourseIdByExamQuestionId(@Param("examQuestionId") Integer examQuestionId);
}
