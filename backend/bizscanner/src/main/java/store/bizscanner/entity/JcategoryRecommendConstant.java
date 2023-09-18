package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class JcategoryRecommendConstant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jcategory_recommend_constant_id")
    private Long id;

    private String careaCode;
    private String careaName;
    private Long salesAmount;
    private Double salesAmountRate;
}
