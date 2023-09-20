package store.bizscanner.dto.request;

import lombok.Getter;

@Getter
public class CareaRecommendRequest {
    private String jcategoryCode;
    private Long firstInvestmentAmount;
    private Integer storeArea;
}
