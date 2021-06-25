package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.UserExamEntity;

@Repository
public interface UserExamRepository extends JpaRepository<UserExamEntity,Integer> {
    UserExamEntity findOneById(Integer id);
    UserExamEntity findOneByIdAndUserId(Integer id,Integer userId);
}
