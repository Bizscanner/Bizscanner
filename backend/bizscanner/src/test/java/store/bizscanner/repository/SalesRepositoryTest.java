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
    public void getBestSalesGender() {


        String bestSalesGender = salesRepository.findBestSalesGender(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));

        Assertions.assertThat(bestSalesGender).isEqualTo("MALE");


    }

    @Test
    public void getBestSalesAge() {

        String bestSalesAge = salesRepository.findBestSalesAge(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
        Assertions.assertThat(bestSalesAge).isEqualTo("THIRTIES");
    }

    @Test
    public void getBestSalesDay() {
        String bestSalesDay = salesRepository.findBestSalesDay(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
        Assertions.assertThat(bestSalesDay).isEqualTo("SATURDAY");
    }

    @Test
    public void getBestSalesTime() {
        String bestSalesTime = salesRepository.findBestSalesTime(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
        Assertions.assertThat(bestSalesTime).isEqualTo("TIME5");
    }

    @Test
    public void getBestJcategory() {
        String bestJcategory = salesRepository.findTopByCareaCodeOrderByQuarterSalesAmountDesc(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND))
                .getJcategoryName();
        Assertions.assertThat(bestJcategory).isEqualTo("양식음식점");
    }



}