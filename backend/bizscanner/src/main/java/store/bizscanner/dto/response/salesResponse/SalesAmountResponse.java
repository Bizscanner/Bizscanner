package store.bizscanner.dto.response.salesResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalesAmountResponse {

    List<Long> quarterlySalesAmountResponses;

    List<Long> daySalesAmountResponses;

    List<Long> timeSalesAmountResponses;

    List<Long> genderSalesAmountResponses;

    List<Long> ageSalesAmountResponses;
}
