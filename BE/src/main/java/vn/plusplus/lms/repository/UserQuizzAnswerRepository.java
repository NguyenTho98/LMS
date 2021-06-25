package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.UserQuizzAnswerEntity;

@Repository
public interface UserQuizzAnswerRepository extends JpaRepository<UserQuizzAnswerEntity, Integer> {
    UserQuizzAnswerEntity findOneById(Integer id);
    UserQuizzAnswerEntity findOneByIdAndUserIdAndStatus(Integer id,Integer userId,String status);
}
