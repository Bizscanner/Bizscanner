package store.bizscanner.entity;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
@Getter
public class Sales {

    @Id
    private Long salesId;

    private String yearCode;

    private String quarterCode;

    private String careaTypeCode;

    private String careaTypeName;

    private String careaCode;

    private String careaName;

    private String jcategoryCode;

    private String jcategoryName;

    private Long quarterSalesAmount;

    private Long quarterSalesCount;

    private Long weekdaySalesAmount;

    private Long mondaySalesAmount;

    private Long tuesdaySalesAmount;

    private Long wednesdaySalesAmount;

    private Long thursdaySalesAmount;

    private Long fridaySalesAmount;

    private Long saturdaySalesAmount;

    private Long sundaySalesAmount;

    @Column(name = "time_1_sales_amount")
    private Long time1SalesAmount;

    @Column(name = "time_2_sales_amount")
    private Long time2SalesAmount;

    @Column(name = "time_3_sales_amount")
    private Long time3SalesAmount;

    @Column(name = "time_4_sales_amount")
    private Long time4SalesAmount;

    @Column(name = "time_5_sales_amount")
    private Long time5SalesAmount;

    @Column(name = "time_6_sales_amount")
    private Long time6SalesAmount;

    private Long maleSalesAmount;

    private Long femaleSalesAmount;

    private Long teensSalesAmount;

    private Long twentiesSalesAmount;

    private Long thirtiesSalesAmount;

    private Long fortiesSalesAmount;

    private Long fiftiesSalesAmount;

    private Long sixtiesSalesAmount;

    private int weekdaySalesCount;

    private int weekendSalesCount;

    private int mondaySalesCount;

    private int tuesdaySalesCount;

    private int wednesdaySalesCount;

    private int thursdaySalesCount;

    private int fridaySalesCount;

    private int saturdaySalesCount;

    private int sundaySalesCount;

    @Column(name = "time_1_sales_count")
    private int time1SalesCount;

    @Column(name = "time_2_sales_count")
    private int time2SalesCount;

    @Column(name = "time_3_sales_count")
    private int time3SalesCount;

    @Column(name = "time_4_sales_count")
    private int time4SalesCount;

    @Column(name = "time_5_sales_count")
    private int time5SalesCount;

    @Column(name = "time_6_sales_count")
    private int time6SalesCount;

    private int maleSalesCount;

    private int femaleSalesCount;

    private Long teensSalesCount;

    private Long twentiesSalesCount;

    private Long thirtiesSalesCount;

    private Long fortiesSalesCount;

    private Long fiftiesSalesCount;

    private Long sixtiesSalesCount;

}
