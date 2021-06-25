package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.CourseEntity;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
    CourseEntity findOneById(Integer id);
    CourseEntity findOneByIdAndStatus(Integer id,String status);

    @Query(nativeQuery = true,value = "SELECT * FROM `course`\n" +
            "WHERE `id` = (SELECT `course_id` FROM `user_course` WHERE `user_id` = :userId)\n" +
            "AND `status` = 'ACTIVE';")
    List<CourseEntity> findAllById(@Param("userId") Integer userId);
}
