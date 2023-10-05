package store.bizscanner.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.earningexpenditure.ConsumptionTrendResponse;
import store.bizscanner.global.exception.CustomException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EarningExpenditureServiceTest {

    @Autowired
    private EarningExpenditureService earningExpenditureService;

    @Test
    public void consumptionTrendSuccess() {
        ConsumptionTrendResponse consumptionTrend = earningExpenditureService.getConsumptionTrend("2110008");
        Assertions.assertThat(consumptionTrend.getTotalExpenditure()).isEqualTo(2746062777L);
    }

    @Test
    public void consumptionTrendFail() {
        Assertions.assertThatThrownBy(() -> earningExpenditureService.getConsumptionTrend("211"))
                .isInstanceOf(CustomException.class)
                .hasMessage("Report Resource not exists");
    }



}