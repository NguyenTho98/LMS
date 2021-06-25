package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.UserRoleEntity;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Integer> {
    //TODO ko can join bang api_id, lay tren bang role_api la co id roi.
    @Query(nativeQuery = true,value = "SELECT api_id FROM `lms_plusplus`.role_api\n" +
            "JOIN `role` ON `role_api`.`role_id` = `role`.`id`\n" +
            "JOIN `user_role` ON `user_role`.`role_id` = `role`.`id`\n" +
            "WHERE `user_role`.`user_id` = :userId")
    List<Integer> getListApiIdByUserId(@Param("userId") Integer userId);
}
