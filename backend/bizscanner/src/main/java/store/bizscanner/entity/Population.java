package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Population {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "population_id")
    private Long id;

    private String yearCode;
    private String quarterCode;
    private String careaTypeCode;
    private String careaTypeName;
    private String careaCode;
    private String careaName;

    private Integer totalPopulation;
    private Integer malePopulation;
    private Integer femalePopulation;
    private Integer teensPopulation;
    private Integer twentiesPopulation;
    private Integer thirtiesPopulation;
    private Integer fortiesPopulation;
    private Integer fiftiesPopulation;
    private Integer sixtiesPopulation;

    @Column(name = "time_1_population")
    private Integer time1Population;
    @Column(name = "time_2_population")
    private Integer time2Population;
    @Column(name = "time_3_population")
    private Integer time3Population;
    @Column(name = "time_4_population")
    private Integer time4Population;
    @Column(name = "time_5_population")
    private Integer time5Population;
    @Column(name = "time_6_population")
    private Integer time6Population;

    private Integer mondayPopulation;
    private Integer tuesdayPopulation;
    private Integer wednesdayPopulation;
    private Integer thursdayPopulation;
    private Integer fridayPopulation;
    private Integer saturdayPopulation;
    private Integer sundayPopulation;

    private Integer maleTeensPopulation;
    private Integer femaleTeensPopulation;
    private Integer maleTwentiesPopulation;
    private Integer femaleTwentiesPopulation;
    private Integer maleThirtiesPopulation;
    private Integer femaleThirtiesPopulation;
    private Integer maleFortiesPopulation;
    private Integer femaleFortiesPopulation;
    private Integer maleFiftiesPopulation;
    private Integer femaleFiftiesPopulation;
    private Integer maleOversixtiesPopulation;
    private Integer femaleOversixtiesPopulation;
}
