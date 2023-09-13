package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Carea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carea_id")
    private Long id;

    private String careaTypeCode;
    private String careaTypeName;
    private String careaCode;
    private String careaName;
    private String sigungu;
    private String dong;
    private Double latitude;
    private Double longitude;
    private Long rentId;
}
