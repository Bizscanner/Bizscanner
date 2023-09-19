package store.bizscanner.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import store.bizscanner.entity.Sales;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;

import java.util.List;


@SpringBootTest
class SalesRepositoryTest {

    @Autowired
    SalesRepository salesRepository;

    private static String careaCode = "1001491";
    private static String jcategoryCode = "CS100007";

    @Test
    void getBestJcategory() {
        String bestJcategory = salesRepository.findTopByCareaCodeOrderByQuarterSalesAmountDesc(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND))
                .getJcategoryName();
        Assertions.assertThat(bestJcategory).isEqualTo("양식음식점");
    }
}