package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.UserCourseEntity;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourseEntity, Integer> {
    UserCourseEntity findOneByIdAndStatus(Integer id,String status);

    UserCourseEntity findOneById(Integer id);

    List<UserCourseEntity> findAllByCourseId(Integer courseId);

    List<UserCourseEntity> findAllByUserId(Integer userId);

    UserCourseEntity findOneByUserIdAndCourseIdAndStatus(Integer userId, Integer courseId, String status);
}
