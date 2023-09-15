package store.bizscanner.dto.response.population;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuarterlyPopulationResponse {
    String yearCode;
    String quarterCode;
    Integer totalPopulation;
}
