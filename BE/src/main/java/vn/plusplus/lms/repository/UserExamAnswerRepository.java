package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.UserExamAnswerEntity;


@Repository
public interface UserExamAnswerRepository extends JpaRepository<UserExamAnswerEntity,Integer> {
    UserExamAnswerEntity findOneById(Integer id);
    UserExamAnswerEntity findOneByIdAndUserId(Integer id,Integer userId);
}
