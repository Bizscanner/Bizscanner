package store.bizscanner.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.global.exception.CustomException;
@SpringBootTest @Transactional
class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @Test
    public void BestJcategory_EmptyCheck() {
        Assertions.assertThatThrownBy(() -> storeService.bestJcategory("123"))
                .isInstanceOf(CustomException.class)
                .hasMessage("Report Resource not exists");
    }

}