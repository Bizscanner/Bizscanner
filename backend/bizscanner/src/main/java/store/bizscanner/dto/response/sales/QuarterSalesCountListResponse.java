package store.bizscanner.dto.response.sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QuarterSalesCountListResponse {

    List<QuarterSalesCountResponse> quarterlySalesCountResponseList;
}
