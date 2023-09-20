package store.bizscanner.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class CareaRecommendNormalizedId implements Serializable {

    private String jcategoryCode;
    private String careaCode;
}
