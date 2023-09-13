package store.bizscanner.dto.response.rent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.bizscanner.entity.Rent;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentResponse {
    Integer rentAmount;
    Double rentIncreaseRate;
    Integer depositAmount;
    Integer monthlyAmount;
    Integer maintenanceAmount;
    Integer firstInvestmentAmount;

    public RentResponse(Rent rent){
        this.rentAmount = rent.getRentAmount();
        this.rentIncreaseRate = rent.getRentIncreaseRate();
        this.depositAmount = rent.getDepositAmount();
        this.monthlyAmount = rent.getMonthlyAmount();
        this.maintenanceAmount = rent.getMaintenanceAmount();
        this.firstInvestmentAmount = rent.getFirstInvestmentAmount();
    }
}
