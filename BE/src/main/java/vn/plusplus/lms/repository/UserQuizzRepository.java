package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.UserQuizzEntity;

@Repository
public interface UserQuizzRepository extends JpaRepository<UserQuizzEntity,Integer> {
    UserQuizzEntity findOneById(Integer id);
    UserQuizzEntity findOneByIdAndUserIdAndStatus(Integer id, Integer userId,String status);
}
