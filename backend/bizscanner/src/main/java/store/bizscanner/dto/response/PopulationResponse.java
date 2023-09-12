package store.bizscanner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PopulationResponse {
    private Integer[] quarterlyPopulation;
    private Integer[] dayPopulation;
    private Integer[] timePopulation;
    private Integer[] genderPopulation;
    private Integer[] agePopulation;
    private Integer[] maleAgePopulation;
    private Integer[] femaleAgePopulation;
}
