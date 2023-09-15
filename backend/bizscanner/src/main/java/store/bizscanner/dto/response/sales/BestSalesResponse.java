package store.bizscanner.dto.response.sales;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestSalesResponse {

    private String bestSalesGender;
    private String bestSalesAge;
    private String bestSalesDay;
    private String bestSalesTime;
    private String bestJcategoryCode;
}