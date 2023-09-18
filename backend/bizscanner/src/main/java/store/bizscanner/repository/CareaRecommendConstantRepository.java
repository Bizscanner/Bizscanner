package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.CareaRecommendConstant;

@Repository
public interface CareaRecommendConstantRepository extends JpaRepository<CareaRecommendConstant, Long> {
}
