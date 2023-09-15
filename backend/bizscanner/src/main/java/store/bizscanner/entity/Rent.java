package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private Long id;

    private String rentCareaName;
    private Integer rentAmount;
    private Integer depositAmount;
    private Integer monthlyAmount;
    private Integer maintenanceAmount;
    private Integer firstInvestmentAmount;
    private Double rentIncreaseRate;
    private Double exclusiveArea;
    private Double latitude;
    private Double longitude;
}
