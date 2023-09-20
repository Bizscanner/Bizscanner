package store.bizscanner.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.entity.CareaRecommendNormalized;
import store.bizscanner.entity.CareaRecommendNormalizedId;

import java.util.*;

@SpringBootTest
@Transactional
class CareaRecommendNormalizedRepositoryTest {

    @Autowired
    private CareaRecommendNormalizedRepository careaRecommendNormalizedRepository;

    @Test
    public void findTest() {
        CareaRecommendNormalizedId id = new CareaRecommendNormalizedId("CS100001", "1001491");
        Assertions.assertThat(careaRecommendNormalizedRepository.findById(id).get().getSalesAmountRate())
                .isEqualTo(0.7684227047528815);
    }

    @Test
    public void queryTest() {
        List<CareaRecommendNormalized> testList = careaRecommendNormalizedRepository.
                findByJcategoryCodeAndFirstInvestmentAmountLessThan("CS200006", 36560000L);
        Assertions.assertThat(testList.size()).isEqualTo(5);
    }
}