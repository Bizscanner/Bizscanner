package store.bizscanner.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long storeId;

    private String yearCode;
    private String quarterCode;
    private String careaTypeCode;
    private String careaTypeName;
    private String careaCode;
    private String careaName;
    private String jcategoryCode;
    private String jcategoryName;
    private Integer storeCount;
    private Integer similarStoreCount;
    private Integer openRate;
    private Integer openStoreCount;
    private Integer closeRate;
    private Integer closeStoreCount;
    private Integer franchiseStoreCount;
}
