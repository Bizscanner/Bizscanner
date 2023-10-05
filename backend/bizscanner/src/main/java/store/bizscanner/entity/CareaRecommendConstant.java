package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CareaRecommendConstant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carea_recommend_constant_id")
    private Long id;

    private String jcategoryCode;
    private String jcategoryName;
    private Long salesAmount;
    private Double salesAmountRate;
}
