package store.bizscanner.dto.response.earningexpenditure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ConsumptionTrendResponse {

    private Long totalExpenditure;
    private Long groceryExpenditure;
    private Long clothExpenditure;
    private Long householdExpenditure;
    private Long medicalExpenditure;
    private Long transportationExpenditure;
    private Long leisureExpenditure;
    private Long cultureExpenditure;
    private Long educationExpenditure;
    private Long pleasureExpenditure;
    private Long earningDecile;
}
