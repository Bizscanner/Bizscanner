package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.Sales;
import store.bizscanner.repository.mapping.SumSalesMapping;
import store.bizscanner.repository.mapping.JcategoryRecommendMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    Optional<Sales> findTopByCareaCodeOrderByQuarterSalesAmountDesc(String careaCode);

    List<Sales> findByCareaCodeAndJcategoryCodeOrderByYearCodeAscQuarterCodeAsc(String careaCode, String jcategoryCode);

    Optional<Sales> findTopByCareaCodeAndJcategoryCode(String careaCode, String jcategoryCode);

    /**
     * 업종 추천 시 사용되는 쿼리
     * @param careaCode
     * @return 상권 내 전체 업종의 예상 매출액
     *
     * 2023 1분기 매출액과 2022 1분기 매출액을 이용하여 변화율을 산출 후 예상 매출액 산정
     */
    @Query("SELECT s.careaCode as careaCode, " +
            "s.jcategoryCode as jcategoryCode, " +
            "s.quarterSalesAmount as quarterSalesAmount, " +
            "ROUND(((s.quarterSalesAmount - l.quarterSalesAmount) / l.quarterSalesAmount + 1) * s.quarterSalesAmount) AS expectAmount " +
            "FROM Sales s " +
            "JOIN Sales l ON s.careaCode = l.careaCode AND s.jcategoryCode = l.jcategoryCode " +
            "WHERE s.yearCode = '2023' AND s.careaCode = :careaCode " +
            "AND l.yearCode = '2022' AND l.quarterCode = '1' " +
            "ORDER BY expectAmount DESC")
    List<JcategoryRecommendMapping> getJcategoryRecommend(@Param("careaCode") String careaCode);

    @Query("SELECT sum(s.mondaySalesAmount) as mondaySalesAmount," +
            "sum (s.tuesdaySalesAmount) as tuesdaySalesAmount, " +
            "sum (s.wednesdaySalesAmount) as wednesdaySalesAmount, " +
            "sum (s.thursdaySalesAmount) as thursdaySalesAmount, " +
            "sum (s.fridaySalesAmount) as fridaySalesAmount, " +
            "sum (s.saturdaySalesAmount) as saturdaySalesAmount, " +
            "sum (s.sundaySalesAmount) as sundaySalesAmount, " +
            "sum (s.time1SalesAmount) as time1SalesAmount, " +
            "sum (s.time2SalesAmount) as time2SalesAmount, " +
            "sum (s.time3SalesAmount) as time3SalesAmount, " +
            "sum (s.time4SalesAmount) as time4SalesAmount, " +
            "sum (s.time5SalesAmount) as time5SalesAmount, " +
            "sum (s.time6SalesAmount) as time6SalesAmount, " +
            "sum (s.maleSalesAmount) as maleSalesAmount, " +
            "sum (s.femaleSalesAmount) as femaleSalesAmount, " +
            "sum (s.teensSalesAmount) as teensSalesAmount, " +
            "sum (s.twentiesSalesAmount) as twentiesSalesAmount, " +
            "sum (s.thirtiesSalesAmount) as thirtiesSalesAmount, " +
            "sum (s.fortiesSalesAmount) as fortiesSalesAmount, " +
            "sum (s.fiftiesSalesAmount) as fiftiesSalesAmount, " +
            "sum (s.sixtiesSalesAmount) as sixtiesSalesAmount " +
            "FROM Sales s " +
            "WHERE s.careaCode = :careaCode " +
            "GROUP BY s.careaCode")
    Optional<SumSalesMapping> findSumSalesDay(@Param("careaCode") String careaCode);
}
