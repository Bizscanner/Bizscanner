package store.bizscanner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "code_coordinate")
@Getter
@NoArgsConstructor
public class CodeCoordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_coordinate_id")
    private Long id;

    private String code;
    private Double latitude;
    private Double longitude;
}
