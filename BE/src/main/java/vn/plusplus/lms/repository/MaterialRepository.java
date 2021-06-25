package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.MaterialEntity;

import java.util.List;


@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity,Integer> {
    MaterialEntity findOneById(Integer id);
    MaterialEntity findOneByIdAndStatus(Integer id,String status);

    List<MaterialEntity> findAllBySessionIdAndStatus(Integer sessionId,String status);

    @Query(nativeQuery = true, value = "SELECT course.id FROM course JOIN lesson ON course.id = lesson.course_id " +
            "JOIN session ON lesson.id = session.lesson_id JOIN material ON session.id = material.session_id \n" +
            "WHERE material.id =:materialId AND material.status='ACTIVE' AND session.status='ACTIVE' AND lesson.status='ACTIVE' AND course.status='ACTIVE';")
    Integer findCourseIdByMaterialId(@Param("materialId") Integer materialId);

    @Query(nativeQuery = true,value = "SELECT * FROM material WHERE material.status='ACTIVE' AND "
        + "material.session_id=:sessionId\n")
    List<MaterialEntity> findMaterialBySessionId(Integer sessionId);
}
