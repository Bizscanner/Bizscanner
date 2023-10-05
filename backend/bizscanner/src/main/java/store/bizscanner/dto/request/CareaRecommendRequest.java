package store.bizscanner.dto.request;

import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
public class CareaRecommendRequest {
    private String jcategoryCode;
    @Min(value = 30_000_000)
    private Long firstInvestmentAmount;
    @Min(value = 1)
    private Integer storeArea;
}
