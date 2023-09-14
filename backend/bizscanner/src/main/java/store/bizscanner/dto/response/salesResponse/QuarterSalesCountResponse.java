package store.bizscanner.dto.response.salesResponse;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class QuarterSalesCountResponse {

    private String yearCode;

    private String quarterCode;

    private Long quarterSalesCount;
}
