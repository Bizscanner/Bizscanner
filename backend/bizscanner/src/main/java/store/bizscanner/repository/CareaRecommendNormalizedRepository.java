package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.CareaRecommendNormalized;
import store.bizscanner.entity.CareaRecommendNormalizedId;

import java.util.List;

@Repository
public interface CareaRecommendNormalizedRepository extends JpaRepository<CareaRecommendNormalized, CareaRecommendNormalizedId> {

    List<CareaRecommendNormalized> findByJcategoryCodeAndFirstInvestmentAmountLessThan
            (@Param("jcategoryCode") String jcategoryCode, @Param("firstInvestmentAmount") Long firstInvestmentAmount);
}
