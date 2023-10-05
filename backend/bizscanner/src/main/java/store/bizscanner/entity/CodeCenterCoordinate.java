package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "code_center_coordinate")
@Getter
@NoArgsConstructor
public class CodeCenterCoordinate {

    @Id
    private String code;
    private Double latitude;
    private Double longitude;
}

