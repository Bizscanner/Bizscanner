package store.bizscanner.dto.response.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.bizscanner.entity.enums.Age;
import store.bizscanner.entity.enums.Day;
import store.bizscanner.entity.enums.Gender;
import store.bizscanner.entity.enums.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestSalesResponse {

    private Gender bestSalesGender;
    private Age bestSalesAge;
    private Day bestSalesDay;
    private Time bestSalesTime;
    private String bestJcategoryCode;
}