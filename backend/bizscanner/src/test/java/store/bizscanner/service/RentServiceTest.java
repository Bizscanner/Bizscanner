package store.bizscanner.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.global.exception.CustomException;

@SpringBootTest
@Transactional
class RentServiceTest {

    @Autowired
    private RentService rentService;

    @Test
    void Rent_EmptyCheck() {
        Assertions.assertThatThrownBy(() -> rentService.getRent("123"))
                .isInstanceOf(CustomException.class)
                .hasMessage("Report Resource not exists");
    }
}
