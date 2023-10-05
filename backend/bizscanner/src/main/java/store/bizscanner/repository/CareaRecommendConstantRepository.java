package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.CareaRecommendConstant;

import java.util.Optional;

@Repository
public interface CareaRecommendConstantRepository extends JpaRepository<CareaRecommendConstant, Long> {

    Optional<CareaRecommendConstant> findByJcategoryCode(@Param("jcategoryCode") String jcategoryCode);
}
