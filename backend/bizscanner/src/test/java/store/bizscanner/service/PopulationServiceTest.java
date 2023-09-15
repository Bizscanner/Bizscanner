package store.bizscanner.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.global.exception.CustomException;

@SpringBootTest
@Transactional
class PopulationServiceTest {

    @Autowired
    private PopulationService populationService;

    @Test
    void Population_EmptyCheck() {
        Assertions.assertThatThrownBy(() -> populationService.getPopulation("123"))
                .isInstanceOf(CustomException.class)
                .hasMessage("Report Resource not exists");
    }

    @Test
    void BestPopulation_EmptyCheck() {
        Assertions.assertThatThrownBy(() -> populationService.bestPopulation("123"))
                .isInstanceOf(CustomException.class)
                .hasMessage("Report Resource not exists");
    }
}
