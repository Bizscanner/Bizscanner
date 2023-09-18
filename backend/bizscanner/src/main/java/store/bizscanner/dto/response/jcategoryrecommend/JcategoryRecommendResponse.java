package store.bizscanner.dto.response.jcategoryrecommend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.bizscanner.entity.JcategoryRecommendConstant;
import store.bizscanner.repository.mapping.JcategoryRecommendMapping;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JcategoryRecommendResponse {
    private String jcategoryCode;
    private Long averageNetProfitByJcategory;
    private Long recommendedNetProfitByJcategory;
    private Double averageSalesAmountRateByJcategory;
    private Double recommendedCareaSalesAmountRateByJcategory;
    private Integer closeRate;

    public JcategoryRecommendResponse(JcategoryRecommendMapping jcategoryRecommendMapping, JcategoryRecommendConstant jcategoryRecommendConstant, Integer closeRate){
        this.jcategoryCode = jcategoryRecommendMapping.getJcategoryCode();
        this.averageNetProfitByJcategory = jcategoryRecommendConstant.getSalesAmount();
        this.recommendedNetProfitByJcategory = jcategoryRecommendMapping.getQuarterSalesAmount();
        this.averageSalesAmountRateByJcategory = jcategoryRecommendConstant.getSalesAmountRate();
        this.recommendedCareaSalesAmountRateByJcategory = Math.round(10000.0 * jcategoryRecommendMapping.getExpectAmount() / jcategoryRecommendMapping.getQuarterSalesAmount()) / 100.0;
        this.closeRate = closeRate;
    }
}
