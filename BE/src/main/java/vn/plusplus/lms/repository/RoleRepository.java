package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
}
