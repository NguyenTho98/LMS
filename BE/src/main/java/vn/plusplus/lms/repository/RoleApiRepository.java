package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.RoleApiEntity;

@Repository
public interface RoleApiRepository extends JpaRepository<RoleApiEntity,Integer> {
}
