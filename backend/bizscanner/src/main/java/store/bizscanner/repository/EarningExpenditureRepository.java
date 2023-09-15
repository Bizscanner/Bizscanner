package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.bizscanner.dto.response.earningexpenditure.ConsumptionTrendResponse;
import store.bizscanner.entity.EarningExpenditure;

import java.util.Optional;

@Repository
public interface EarningExpenditureRepository extends JpaRepository<EarningExpenditure,Long> {

    @Query("SELECT new store.bizscanner.dto.response.earningexpenditure.ConsumptionTrendResponse" +
            "(e.totalExpenditure, e.groceryExpenditure, e.clothExpenditure, e.householdExpenditure, " +
            "e.medicalExpenditure, e.transportationExpenditure, e.leisureExpenditure, e.cultureExpenditure, " +
            "e.educationExpenditure, e.pleasureExpenditure, e.earningDecile)" +
            " FROM EarningExpenditure e WHERE e.careaCode = :careaCode AND e.yearCode = '2022' AND e.quarterCode = '4' ")
    Optional<ConsumptionTrendResponse> getConsumptionTrend(@Param("careaCode") String careaCode);

}