package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.JcategoryRecommendConstant;

import java.util.Optional;

@Repository
public interface JcategoryRecommendConstantRepository extends JpaRepository<JcategoryRecommendConstant, Long> {
    Optional<JcategoryRecommendConstant> findByCareaCode(String careaCode);
}
