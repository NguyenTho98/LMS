package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.plusplus.lms.repository.entities.ApiEntity;


public interface ApiRepository extends JpaRepository<ApiEntity, Integer> {
    ApiEntity findOneById(Integer id);


}
