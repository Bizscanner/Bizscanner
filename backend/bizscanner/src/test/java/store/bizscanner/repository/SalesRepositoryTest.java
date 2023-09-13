package store.bizscanner.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import store.bizscanner.entity.Sales;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesRepositoryTest {

    @Autowired
    SalesRepository salesRepository;

    private static String careaCode = "1001491";

    @Test
    public void getBestSalesGender() {


        String bestSalesGender = salesRepository.findBestSalesGender(careaCode);

        Assertions.assertThat(bestSalesGender).isEqualTo("male");


    }

    @Test
    public void getBestSalesAge() {

        String bestSalesAge = salesRepository.findBestSalesAge(careaCode);
        Assertions.assertThat(bestSalesAge).isEqualTo("thirty");
    }

    @Test
    public void getBestSalesDay() {
        String bestSalesDay = salesRepository.findBestSalesDay(careaCode);
        Assertions.assertThat(bestSalesDay).isEqualTo("saturday");
    }

    @Test
    public void getBestSalesTime() {
        String bestSalesTime = salesRepository.findBestSalesTime(careaCode);
        Assertions.assertThat(bestSalesTime).isEqualTo("time_5");
    }

    @Test
    public void getBestJcategory() {
        String bestJcategory = salesRepository.findTopByCareaCodeOrderByQuarterSalesAmountDesc(careaCode).getJcategoryName();
        Assertions.assertThat(bestJcategory).isEqualTo("양식음식점");
    }



}