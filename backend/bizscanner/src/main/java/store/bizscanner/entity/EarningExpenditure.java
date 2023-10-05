package store.bizscanner.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "earning_expenditure")
@Getter
public class EarningExpenditure {

    @Id
    private Long earningExpenditureId;
    private String yearCode;
    private String quarterCode;
    private String careaTypeCode;
    private String careaTypeName;
    private String careaCode;
    private String careaName;
    private Long monthAvgEarning;
    private Long earningDecile;
    private Long totalExpenditure;
    private Long groceryExpenditure;
    private Long clothExpenditure;
    private Long householdExpenditure;
    private Long medicalExpenditure;
    private Long transportationExpenditure;
    private Long leisureExpenditure;
    private Long cultureExpenditure;
    private Long educationExpenditure;
    private Long pleasureExpenditure;
}
