package store.bizscanner.dto.response.carearecommend;

import lombok.Getter;
import store.bizscanner.entity.CareaRecommendNormalized;

@Getter
public class CareaRecommendResponse {
    private final String careaCode;
    private final Long averageNetProfitByJcategory;
    private final Long recommendedNetProfit;
    private final Double averageSalesAmountRateByJcategory;
    private final Double recommendedSalesAmountRate;
    private final Integer averageTotalPopulation;
    private final Integer totalPopulation;
    private final String careaChange;

    public CareaRecommendResponse(Long averageNetProfitByJcategory, Long recommendedNetProfit, Double averageSalesAmountRateByJcategory, CareaRecommendNormalized careaRecommended) {
        this.careaCode = careaRecommended.getCareaCode();
        this.averageNetProfitByJcategory = averageNetProfitByJcategory;
        this.recommendedNetProfit = recommendedNetProfit;
        this.averageSalesAmountRateByJcategory = averageSalesAmountRateByJcategory;
        this.recommendedSalesAmountRate = careaRecommended.getSalesAmountRate();
        //모든 상권의 평균 유동 인구
        this.averageTotalPopulation = 837020;
        this.totalPopulation = careaRecommended.getTotalPopulation();
        this.careaChange = careaRecommended.getCareaChange();
    }
}
