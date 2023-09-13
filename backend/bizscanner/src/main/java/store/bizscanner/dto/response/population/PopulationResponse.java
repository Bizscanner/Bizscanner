package store.bizscanner.dto.response.population;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.bizscanner.entity.Population;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PopulationResponse {
    private List<QuarterlyPopulationResponse> quarterlyPopulation;
    private Integer[] dayPopulation;
    private Integer[] timePopulation;
    private Integer[] genderPopulation;
    private Integer[] agePopulation;
    private Integer[] maleAgePopulation;
    private Integer[] femaleAgePopulation;

    public PopulationResponse(List<QuarterlyPopulationResponse> quarterlyPopulation, Population population){
        this.quarterlyPopulation = quarterlyPopulation;
        this.dayPopulation = new Integer[] {
                population.getMondayPopulation(),
                population.getTuesdayPopulation(),
                population.getWednesdayPopulation(),
                population.getThursdayPopulation(),
                population.getFridayPopulation(),
                population.getSaturdayPopulation(),
                population.getSundayPopulation()
        };
        this.timePopulation = new Integer[] {
                population.getTime1Population(),
                population.getTime2Population(),
                population.getTime3Population(),
                population.getTime4Population(),
                population.getTime5Population(),
                population.getTime6Population(),
        };
        this.genderPopulation = new Integer[] {
                population.getMalePopulation(),
                population.getFemalePopulation()
        };
        this.agePopulation = new Integer[] {
                population.getTeensPopulation(),
                population.getTwentiesPopulation(),
                population.getThirtiesPopulation(),
                population.getFortiesPopulation(),
                population.getFiftiesPopulation(),
                population.getSixtiesPopulation()
        };
        this.maleAgePopulation = new Integer[] {
                population.getMaleTeensPopulation(),
                population.getMaleTwentiesPopulation(),
                population.getMaleThirtiesPopulation(),
                population.getMaleFortiesPopulation(),
                population.getMaleFiftiesPopulation(),
                population.getMaleOversixtiesPopulation()
        };
        this.femaleAgePopulation = new Integer[] {
                population.getFemaleFortiesPopulation(),
                population.getFemaleTwentiesPopulation(),
                population.getFemaleThirtiesPopulation(),
                population.getFemaleFortiesPopulation(),
                population.getFemaleFiftiesPopulation(),
                population.getFemaleOversixtiesPopulation()
        };
    }
}
