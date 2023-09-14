package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Cchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cchange_id")
    private Long id;

    private String yearCode;
    private String quarterCode;
    private String careaTypeCode;
    private String careaTypeName;
    private String careaCode;
    private String careaName;
    private String careaChange;
    private String careaChangeName;
    private Integer runAverage;
    private Integer closeAverage;
}
