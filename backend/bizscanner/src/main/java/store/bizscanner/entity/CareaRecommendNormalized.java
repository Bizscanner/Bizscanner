package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "carea_recommend_normalized")
@IdClass(CareaRecommendNormalizedId.class)
@Getter
@NoArgsConstructor
@ToString
public class CareaRecommendNormalized {

    @Id
    private String jcategoryCode;
    @Id
    private String careaCode;
    private Long monthlySalesAmount;
    private Integer rentAmount;
    private Long firstInvestmentAmount;
    private Double salesAmountRate;
    private Double salesAmountRateNormalized;
    private Double populationNormalized;
    private Double careaChangeNormalized;
    private Integer totalPopulation;
    private String careaChange;

    public Long getProfit(Long storeArea) {
        return this.monthlySalesAmount - this.rentAmount * storeArea;
    }

    public Double getScore(Long minProfit, Long maxProfit, Long storeArea) {
        //scoring 가중치 계수
        double c1 = 0.65;
        double c2 = 0.15;
        double c3 = 0.15;
        double c4 = 0.05;

        //순이익 정규화
        double profit = getProfit(storeArea);
        double profitNormalized = (profit - minProfit) / (maxProfit - minProfit);

        //scoring 알고리즘
        return c1 * profitNormalized + c2 * salesAmountRateNormalized + c3 * populationNormalized + c4 * careaChangeNormalized;

    }

}
