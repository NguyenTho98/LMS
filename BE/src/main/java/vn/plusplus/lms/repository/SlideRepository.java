package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.SessionEntity;
import vn.plusplus.lms.repository.entities.SlideEntity;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<SlideEntity,Integer> {
    SlideEntity findOneById(Integer slideId);

    SlideEntity findSlideByIdAndStatus(Integer slideId , String status);


    List<SlideEntity> findAllByMaterialIdAndStatus(Integer materialId,String status);

    @Query(nativeQuery = true,value = "SELECT course.id FROM course \n" +
            "JOIN lesson ON course.id = lesson.course_id \n" +
            "JOIN session ON lesson.id = session.lesson_id \n" +
            "JOIN material ON session.id = material.session_id \n" +
            "JOIN slide ON material.id = slide.material_id\n" +
            "WHERE slide.id =:slideId\n" +
            "AND slide.status='ACTIVE'\n" +
            "AND material.status='ACTIVE' \n" +
            "AND session.status='ACTIVE' \n" +
            "AND lesson.status='ACTIVE'\n" +
            "AND course.status='ACTIVE';")
    Integer findCourseIdBySlideId(@Param("slideId") Integer slideId);

}
