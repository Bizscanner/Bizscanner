package store.bizscanner.dto.response.salesResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class QuarterSalesCountListResponse {

    List<QuarterSalesCountResponse> quarterSalesCountResponseList;
}
