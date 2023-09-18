package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.JcategoryRecommendConstant;

@Repository
public interface JcategoryRecommendConstantRepository extends JpaRepository<JcategoryRecommendConstant, Long> {
}
