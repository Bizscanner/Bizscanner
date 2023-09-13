package store.bizscanner.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void bestStoreCountJcategory() {
        List<String> maxStoreCount = storeRepository.findMaxStoreCount("2130324", "2023");
        Assertions.assertThat(maxStoreCount.get(0)).isEqualTo("일반의류");
    }

    @Test
    public void bestOpenStoreCountJcategory() {
        List<String> maxOpenStoreCounts = storeRepository.findMaxOpenStoreCount("2130128", "2023");
        Assertions.assertThat(maxOpenStoreCounts.get(0)).isEqualTo("한식음식점");
    }

    @Test
    public void bestCloseStoreCountJcategory() {
        List<String> maxCloseStoreCounts = storeRepository.findMaxCloseStoreCount("1001495", "2023");
        Assertions.assertThat(maxCloseStoreCounts.get(0)).isEqualTo("한식음식점");
    }
}